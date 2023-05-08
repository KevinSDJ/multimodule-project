import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import Sse from './pages/Sse.tsx'
import Auth2 from './pages/Auth2.tsx'

const router= createBrowserRouter([
  {
    path:"/",
    element:<App/>,
    children:[
    {
      path:"sse",
      element:<Sse/>
    },
    {
      path:"auth2",
      element:<Auth2/>
    }
  ]
  }
])

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
