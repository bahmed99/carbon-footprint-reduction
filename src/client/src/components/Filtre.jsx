import React, {useEffect , useState} from 'react'
import { Checkbox } from 'antd';

export default function Filtre(props) {

  return (
    <div className='ContainerFiltre'>
      {props.name}
      <br />
      <Checkbox onChange={(e)=>console.log(e.target.value)}value={"a"}>BMW (5)</Checkbox>
      <br />
      <Checkbox>MERCEDES BENZ</Checkbox>
    </div>
  )
}
