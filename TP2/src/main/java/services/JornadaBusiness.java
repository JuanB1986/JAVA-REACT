package services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import entities.ConceptoLaboral;
import entities.Empleado;
import entities.Jornada;
import entities.JornadaRequest;
import repositories.EmpleadoRepository;
import repositories.JornadaRepository;
import userExceptions.userHttp404Status;

public class JornadaBusiness {

	public static void BusinessRules(JornadaRequest jornadaRequest, ConceptoLaboral conceptoLaboral,JornadaRepository jornadaRepository, EmpleadoRepository empleadoRepository) {

		/**
		 * Variables globales para uso en funciones de filtros.
		 */
		List<Jornada> listaJornadas = jornadaRepository.findAll();
		Empleado empleadoBuscado = empleadoRepository.getReferenceById(jornadaRequest.getIdEmpleado());
		List<Jornada> listaFiltrada=  new ArrayList<>();

		/**
		 * Relga de negocio: 1	(OK)
		 * Limite de horas trabajadas maxima y minimas para cierto concepto.
		 */
		if (jornadaRequest.getIdConcepto() != 3 && ((jornadaRequest.getHorasTrabajadas() > conceptoLaboral.getHs_maximo()) ||
			 jornadaRequest.getHorasTrabajadas() < conceptoLaboral.getHs_minimo())
			)
		{
			throw new userHttp404Status("El rango de horas que se puede cargar para este concepto es de "+conceptoLaboral.getHs_minimo()
					+ " Hs Minimas y "+conceptoLaboral.getHs_maximo()+" Hs. Maxima");
		}


		/**
		 * Regla de negocio 2.	(OK)
		 */
        int horasUsuarioIngresado = listaJornadas.stream()
                .filter(j -> j.getNroDocumento() == empleadoBuscado.getNroDocumento() && j.getFecha().equals(jornadaRequest.getFecha()))
                .mapToInt(Jornada::getHorasTrabajadas)
                .sum() + jornadaRequest.getHorasTrabajadas();

        if (horasUsuarioIngresado > 14) {
            throw new userHttp404Status("Un empleado no puede cargar más de 14 horas trabajadas en un día");
        }


        /**
         * Regla 3	(OK)
         */
        listaFiltrada = filtrarPorSemana(jornadaRequest.getFecha(), listaJornadas);

        listaFiltrada = listaFiltrada.stream().filter(m -> m.getNroDocumento().equals(empleadoBuscado.getNroDocumento())).toList();

        int horasSemanales=0;

        for (Jornada lista : listaFiltrada) {
        	horasSemanales += lista.getHorasTrabajadas();
        }

        if (horasSemanales +jornadaRequest.getHorasTrabajadas() > 52) {
            throw new userHttp404Status("El empleado ingresado supera las 52 horas semanales ("+horasSemanales+")");
        }


		/**
		 * Regla de negocio 4.		(OK)
		 * Maximo 190 hs mensuales.
		 */
        int horasMensual = listaJornadas.stream()
                .filter(j -> j.getNroDocumento().equals(empleadoBuscado.getNroDocumento()) && isSameMonth(j.getFecha(), jornadaRequest.getFecha()))
                .mapToInt(Jornada::getHorasTrabajadas)
                .sum() + jornadaRequest.getHorasTrabajadas();

        if (horasMensual > 190) {
            throw new userHttp404Status("El empleado ingresado supera las 190 horas mensuales("+horasMensual+" Hs.)");
        }


        /**
         * Regla 5	(OK)
         */
        String fechaAConsultar = jornadaRequest.getFecha();

        listaFiltrada = listaJornadas.stream()
                .filter(j -> j.getFecha().equals(fechaAConsultar) &&
                			 j.getConcepto().equals("Día Libre") &&
                             j.getNroDocumento().equals(empleadoBuscado.getNroDocumento()))
                .collect(Collectors.toList());

        if (listaFiltrada.size()>0) {
            throw new userHttp404Status("El empleado ingresado cuenta con un día libre en esa fecha.");
        }


        /**
         * Regla 6	(OK)
         */
        long turnosExtraEnSemana = listaJornadas.stream()
                .filter(j -> j.getNroDocumento().equals(empleadoBuscado.getNroDocumento()) &&
                        isSameWeek(j.getFecha(), jornadaRequest.getFecha()) &&
                        j.getConcepto().equals("Turno Extra"))
                .count();

        if (turnosExtraEnSemana >= 3) {
            throw new userHttp404Status("El empleado ingresado ya cuenta con 3 turnos extra esta semana.");
        }



        /**
         * Regla 8	(OK)
         */
        listaFiltrada = filtrarPorSemana(jornadaRequest.getFecha(), listaJornadas);

        listaFiltrada = listaFiltrada.stream().filter(m -> m.getNroDocumento().equals(empleadoBuscado.getNroDocumento())).toList();

        int cantDiasLibres=0;

        for (Jornada lista : listaFiltrada) {
        	 if(lista.getConcepto().equals("Día Libre")) {
        		 ++cantDiasLibres;
        	 }
        }

        if (jornadaRequest.getIdConcepto().equals(3)) {
        	cantDiasLibres++;
        }

        if (cantDiasLibres > 2) {
            throw new userHttp404Status("El empleado no puede tener más de 2 días libres semanales."+cantDiasLibres);
        }



        /**
         * Regla 9 (OK) esta
         */
        listaFiltrada = listaJornadas.stream()
                .filter(j -> j.getNroDocumento().equals(empleadoBuscado.getNroDocumento()) &&
                isSameMonth(j.getFecha(), jornadaRequest.getFecha())&&
                j.getConcepto().equals("Día Libre")
                ).toList();


        if (listaFiltrada.size() >= 5) {
            throw new userHttp404Status("“El empleado no cuenta con más días libres esta mes.");
        }


        /**
         * Regla 11.
         */
        String[] concepto = new String[3];
        concepto[0]="Turno Normal";
        concepto[1]="Turno Extra";
        concepto[2]="Día Libre";

        listaFiltrada = listaJornadas.stream()
                		.filter(j -> j.getNroDocumento().equals(empleadoBuscado.getNroDocumento()) &&
                        j.getFecha().equals(jornadaRequest.getFecha()) &&
                        j.getConcepto().equals(concepto[jornadaRequest.getIdConcepto()-1])).toList();

        if (listaFiltrada.size()>0) {
            throw new userHttp404Status("No es posible cargar para un empleado en una misma fecha, dos jornadas con el mismo concepto.");
        }


	}



    private static boolean isSameWeek(String fecha1, String fecha2) {
        LocalDate date1 = LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date1.getYear() == date2.getYear() && date1.get(WeekFields.of(Locale.getDefault()).weekOfYear()) == date2.get(WeekFields.of(Locale.getDefault()).weekOfYear());
    }

    private static boolean isSameMonth(String fecha1, String fecha2) {
        LocalDate date1 = LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();
    }


    public static List<Jornada> filtrarPorSemana(String fechaStr, List<Jornada> lista) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fecha = LocalDate.parse(fechaStr, formatter);

        LocalDate primerDiaDeLaSemana = fecha.with(DayOfWeek.MONDAY);
        LocalDate ultimoDiaDeLaSemana = primerDiaDeLaSemana.plusDays(6);

        List<String> diasDeLaSemana = primerDiaDeLaSemana.datesUntil(ultimoDiaDeLaSemana.plusDays(1))
                .map(dia -> dia.format(formatter))
                .collect(Collectors.toList());

        return lista.stream()
                .filter(concepto -> diasDeLaSemana.contains(concepto.getFecha()))
                .collect(Collectors.toList());
    }
}
