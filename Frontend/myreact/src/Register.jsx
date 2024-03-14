import axios from 'axios';
import React, { useState } from 'react';
import { Container, Form, Button, DropdownButton, Dropdown } from 'react-bootstrap';
import GetServerUrl from './GetServerUrl';
import { ToastContainer, toast } from 'react-toastify';


const Register = () => {
    const serverUrl = GetServerUrl();
    const [fullName, setFullName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [license, setLicense] = useState('');
    const [payload, setPayload] = useState(null);
    const [register, setRegister] = useState('');
  
    const registerUser = async() => {
      try{
        const registrationData = {
            "fullName":fullName,
            "email":email,
            "password":password,
            "license":license
          };
          setPayload(registrationData);
          const resp = await axios.post(`${serverUrl}/common/${register}`, registrationData);
          if(resp.status === 200){
            toast("Registered succesfully");
          }
          else{
            toast.error("Registration failed");
          }
      }
      catch(ex){
        toast.error("Registration failed");
      }
    };
  
    return (
      <>
      <Container className="mt-5">
        <h2 className="text-center mb-4">Registration</h2>
        <DropdownButton title="Select Login Type">
            <Dropdown.Item onClick={()=>{setRegister("PharmRegister")}}>Pharmacist</Dropdown.Item>
            <Dropdown.Item onClick={()=>{setRegister("DistRegister")}}>Distributor</Dropdown.Item>
        </DropdownButton>
        <Form>
          <Form.Group controlId="formBasicFullName">
            <Form.Label>Full Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter full name"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
            />
          </Form.Group>
  
          <Form.Group controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Form.Group>
  
          <Form.Group controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Group>
  
          <Form.Group controlId="formBasicLicense">
            <Form.Label>License (ONLY FOR PHARMACISTS)</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter license"
              value={license}
              onChange={(e) => setLicense(e.target.value)}
            />
          </Form.Group>
  
          <Button variant="primary" onClick={registerUser} className="w-100">
            Register
          </Button>
        </Form>
      </Container>
      <ToastContainer></ToastContainer>
      </>
    );
  };
  
  export default Register;