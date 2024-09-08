import React, { useEffect, useState } from 'react'
import Puntajes from './Puntajes';
import '../Styles/Juego.css'
import imagen from '../Img/pxfuel.jpg'


const Juego = () => {
    
    const PUNTAJE_INICIAL = 20;

    const puntajesObject = {
        pun:0,
        ron:0
    }

    const [puntosAcumulados, setPuntosAcumulados]=useState([]);

    const [ronda, setRonda]=useState(0);
    const [puntaje, setPuntaje]=useState(PUNTAJE_INICIAL);
    const [partida, setPartida] = useState(false);
    const [numeroAdivinar, setNumeroAdivinar]= useState(0);
    const [numeroIngresado, setNumeroIngresado]=useState(0);
    const [mensaje, setMensaje]=useState("");
    const [puntajeMasAlto, setPuntajeMasAlto]=useState(" - ");

    const iniciarPartida = () => {
        setPuntaje(PUNTAJE_INICIAL);
        setPartida(true);
        setNumeroAdivinar(Math.floor(Math.random()*20)+1);
        setRonda(ronda+1);
    }

    const inputNumero = (event) =>{      
        setNumeroIngresado(event.target.value);
    }


    useEffect(() => {
        calcularPuntajeMasAlto();
    });

    const calcularPuntajeMasAlto = () =>{
        var punto=0;
        var ronda=0;  
        puntosAcumulados.map( m=> 
        {
            if(m.pun > punto){
                punto = m.pun;
                ronda = m.ron;
            }
        });
        setPuntajeMasAlto(punto + " puntos en ronda: "+ronda)       
    }

    const botonAdivinarNumero = () =>{
        
        if (21>numeroIngresado>0)
        {
            setMensaje("");

            if (numeroIngresado == numeroAdivinar){
                setMensaje( "Acertaste, Ganaste la ronda !")
                setPartida(false);    

                puntajesObject.pun=puntaje;
                puntajesObject.ron=ronda;
                setPuntosAcumulados(m=>[...m,puntajesObject]);            
                calcularPuntajeMasAlto();           
            }
            else
            {            
                if (numeroIngresado>numeroAdivinar)
                {                
                    setMensaje("Pista: Te pasaste")
                }
                
                if (numeroIngresado<numeroAdivinar)
                {                
                    setMensaje("Pista: Te quedaste corto !")
                }

                setPuntaje(puntaje-1);

                if (puntaje<=1){
                    setMensaje("Perdiste la Ronda !")
                    setPartida(false);
    
                    puntajesObject.pun=0;
                    puntajesObject.ron=ronda;
                    setPuntosAcumulados(m=>[...m,puntajesObject]);  
                    calcularPuntajeMasAlto();                     
                }
            }
        }
        else
        {
            setMensaje("Ingrese un número entre 1 y 20");
        }
        
    }


  return (
    <div id='fondo'>
        <header>
            <h2>Java/React LAB - TP1: Juego</h2>   
            <p>Instrucciones: Presionar el botón 'Comenzar Ronda' e Ingresar
                números entre 1 y 20, una vez ingresado, presionar el botón 'Adivinar'.
                Aparecerán pistas en la etiqueta debajo de los botones.
            </p>
        </header>

        <div id='contenedor'>
            <label id='labelInpu1'>Ingresar número</label>
            <br></br>
            <input id='input1' type='number' disabled={!partida} onChange={inputNumero}></input>               
            <p id='lblPuntaje'>Puntaje actual: {puntaje}</p>
            <p id='lblPuntaje2'>Puntaje más alto: {puntajeMasAlto}</p>
            <hr></hr>
            <button class='boton' disabled={!partida} onClick={botonAdivinarNumero} >Adivinar</button>
            <button class='boton' disabled={partida} onClick={iniciarPartida}>Comenzar Ronda</button>
            
            <hr></hr>
            <p id='labelMensaje'>{mensaje}</p>
        </div>

        <hr></hr>
        <Puntajes props={puntosAcumulados}></Puntajes>      
    </div>
  )
}

export default Juego
