import React from 'react'
import Filtre from './Filtre'

export default function Filtres() {
  return (
    <div className='ContainerFilters'>
        <Filtre name={"Marque"}/>
        <Filtre name={"Modele"}/>
        <Filtre name={"Option"}/>
        <Filtre name={"Couleur"}/>
    </div>
  )
}
