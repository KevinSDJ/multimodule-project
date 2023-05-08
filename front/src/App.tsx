import { useEffect } from 'react'
import './App.css'
import { Link, Outlet } from 'react-router-dom';

function App() {

  /*useEffect(()=>{
    let eventSource= new EventSource("http://localhost:8080/");
    eventSource.onerror=(e:Event)=> console.log(e)
    eventSource.onopen=()=> console.log("open sse connection")
    eventSource.onmessage=((e:any)=>{
         const{created_at,message}= Array.of(JSON.parse(e?.data))[0][1]?.data
         let ol= document.getElementById("list-message")
         let li= document.createElement('li');
         li.innerText=`${message}, ${new Date(Date.parse(created_at)).toDateString()}`
         ol?.append(li)
       })
    return ()=> {
      eventSource.close()
    }
  },[])*/
  

  return (
    <>
      <div>
        <header>
          <ul>
            <li><Link to={"sse"}>sse demo</Link></li>
            <li><Link to={"auth2"}>auth2-demo</Link></li>
          </ul>
        </header>
        <Outlet/>
      </div>
    </>
  )
}

export default App
