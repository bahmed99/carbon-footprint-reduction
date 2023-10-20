import React, {useEffect,useState} from 'react'
import axios from 'axios'

export default function HeaderTest() {
  const [data, setData] = useState([])

  useEffect(() => {
    axios.get(process.env.REACT_APP_API_URL+"test").then((res) => {
      setData(res.data.message)
    }).catch((err) => {
      console.log(err)
    })
  
  }, [])

  return (
    <div className='TestClass'>{data}</div>
  )
}
