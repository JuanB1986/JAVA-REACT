import React from 'react'
import '../Styles/Puntajes.css'

const Puntajes = ({props}) => {
  return (
    <table>
      <caption>Puntajes por ronda</caption>
      <tbody>
        <tr>
            <th>RONDA</th>
            <th>PUNTAJE</th>
        </tr>
        {
            props.map( (m,index)=> (
                <tr key={index}>       
                    <td>{m.ron}</td>
                    <td>{m.pun}</td>
                </tr>
            ))
        }
      </tbody>
    </table>
  )
}

export default Puntajes
