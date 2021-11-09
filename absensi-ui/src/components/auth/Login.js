import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Redirect } from 'react-router-dom'
import './Login.css'

import { login } from '../../actions/authAction'

const Login = () => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const { isLoggedIn } = useSelector((state) => state.auth)
  const { message } = useSelector(state => state.message)

  const dispatch = useDispatch()

  if (isLoggedIn) {
    return <Redirect to='/' />
  }

  const handleChange = (e) => {
    let input = e.target
    if (input.name === 'username') {
      setUsername(input.value)
    } else if (input.name === 'password') {
      setPassword(input.value)
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    dispatch(login(username, password))
      .then(() => {
        window.location.reload()
      })
      .catch(() => {
        console.log('error');
      }) 
  }

  // const validate = () => {
  //   let errors = {}
  //   let isValid = true
  //   if (username) {
  //     isValid = false
  //     errors['username'] = 'Please enter your username.'
  //   }

  //   if (typeof input['username'] !== 'undefined') {
  //     isValid = false
  //     const re = /^\S*$/
  //     if (input['username'].length < 6 || !re.test(input['username'])) {
  //       isValid = false
  //       errors['username'] = 'Please add at least 6 characher'
  //     }
  //   }

  //   if (!input['password']) {
  //     isValid = false
  //     errors['password'] = 'Please enter your password'
  //   }

  //   if (typeof input['password'] !== 'undefined') {
  //     isValid = false
  //     if (input['password'].length < 6) {
  //       isValid = false
  //       errors['password'] = 'Please add at least 6 charachter.'
  //     }
  //   }

  //   return isValid
  // }

  return (
    <div className='container'>
      <div className='login-wrapper fadeInDown'>
        <h1>Please Log In</h1>
        <form onSubmit={handleSubmit}>
          <label>
            <p>Username</p>
            <input
              className='form-control'
              type='text'
              name='username'
              value={username}
              onChange={handleChange}
              placeholder='Enter Username'
              id='username'
            />
            {/* <div className='text-danger'>{state.errors.username}</div> */}
          </label>
          <label>
            <p>Password</p>
            <input
              type='password'
              name='password'
              value={password}
              onChange={handleChange}
              className='form-control'
              placeholder='Enter password'
              id='password'
            />
            {/* <div className='text-danger'>{state.errors.password}</div> */}
          </label>
          {message && (
            <div className='form-group'>
              <div className='alert alert-danger' role='alert'>
                <h6>{ message }</h6>
              </div>
            </div>
          )}
          <div>
            <button
              id='btnSubmit'
              className='btn btn-outline-secondary'
              type='submit'
              onSubmit={handleSubmit}
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default Login
