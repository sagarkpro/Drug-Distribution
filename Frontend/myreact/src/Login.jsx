import React, { useState } from 'react';
import { Container, Form, Button, Dropdown, DropdownButton } from 'react-bootstrap';
import axios from 'axios';
import GetServerUrl from './GetServerUrl';
import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

function Login () {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginType, setLoginType] = useState('');
    const serverUrl = GetServerUrl();
    const navigate = useNavigate();

    const sendLoginPayload = async()=>{
        try{
            if(loginType === '') toast.error("please select login type");
            else{
                const resp = await axios.post(`${serverUrl}/common/${loginType}`, {"email":email, "password":password});
                if(resp.status === 200){
                    toast("logged in succesfully");
                }
                sessionStorage.setItem("id", resp.data.id);
                sessionStorage.setItem("name", resp.data.fullName);
                sessionStorage.setItem("userType", resp.data.userType);
                navigate("/home");
            }
        }
        catch(ex){
            toast.error("login failed");
        }
        
    }
        

    return (
        <>
            <Container className="mt-5">
            <h2 className="text-center mb-4">Login</h2>
            <DropdownButton title="Select Login Type">
                <Dropdown.Item onClick={()=>{setLoginType("PharmLogin")}}>Pharmacist Login</Dropdown.Item>
                <Dropdown.Item onClick={()=>{setLoginType("DistLogin")}}>Distributor Login</Dropdown.Item>
            </DropdownButton>
            <Form>
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

                <Button variant="primary" className="w-100 mt-3" onClick={sendLoginPayload}>
                Login
                </Button>
            </Form>
            </Container> 
            <ToastContainer></ToastContainer>
        </>
        
    );
};

export default Login;
