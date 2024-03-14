import { Route, Routes } from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import Home from "./Home";
import { Navbar, Nav, Container } from "react-bootstrap";
import ViewMedicine from "./ViewMedicine";
import ViewOrders from "./ViewOrders";
import SellMedicines from "./SellMedicines";

function Forwarding(){
    return (
        <>
            <Navbar bg="primary" data-bs-theme="dark">
                <Container>
                <Navbar.Brand href="/home">Pharmacy Distribution</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/home">Home</Nav.Link>
                    <Nav.Link href="/login">Login</Nav.Link>
                    <Nav.Link href="/register">Register</Nav.Link>
                    <Nav.Link href="/orders">Orders</Nav.Link>
                    <Nav.Link href="/sellMedicines">Sell Medicines</Nav.Link>
                </Nav>
                </Container>
            </Navbar>
            <Routes>
                <Route index element={<Home/>}></Route>
                <Route path="/home" element={<Home/>}></Route>
                <Route path="/login" element={<Login/>}></Route>
                <Route path="/register" element={<Register/>}></Route>
                <Route path="/viewMedicine" element={<ViewMedicine/>}></Route>
                <Route path="/orders" element={<ViewOrders></ViewOrders>}></Route>
                <Route path="/sellMedicines" element={<SellMedicines/>}></Route>
            </Routes>
        </>
    )
}

export default Forwarding;