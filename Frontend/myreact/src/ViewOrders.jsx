import axios from "axios";
import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import GetServerUrl from "./GetServerUrl";

function ViewOrders(){
    const currentUserId = sessionStorage.getItem("id");
    const currentUserName = sessionStorage.getItem("name");
    const userType = sessionStorage.getItem("userType");
    const [orders, setOrders] = useState([]);
    const serverUrl = GetServerUrl();

    const getOrders = async()=>{
        console.log(userType);
        console.log(typeof(userType));
        let url = '';
        if(userType === 'Pharmacist') url = `${serverUrl}/pharmacist/viewOrders/${currentUserId}`;
        else url = `${serverUrl}/distributor/viewOrders/${currentUserId}`;

        const resp = await axios.get(url);
        if(resp.status === 200){
            setOrders(resp.data);
        }
    }
    useEffect(()=>{
        getOrders();
    }, []);
    if(currentUserId == null) {
        toast.error("pleaser login first");
        return (
            <>
                <ToastContainer></ToastContainer>
            </>
        );
    }
    
    else{
        
        return (
            <>
                <div className="container">
                <h4 style={{"textAlign":"center"}}>Welcome, {currentUserName}</h4>
            <table className="table mt-3">
                <thead>
                    <tr>
                        <th scope="col">Medicine Name</th>
                        <th scope="col">Distributor Name</th>
                        <th scope="col">Pharmacist Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total Amount</th>
                    </tr>
                </thead>
                <tbody>
                {orders.map((order, index)=>{
                    return(
                        <tr key={index}>
                            <td>{order["medicineName"]}</td>
                            <td>{order["distributorName"]}</td>
                            <td>{order["pharmacistName"]}</td>
                            <td>{order["quantity"]}</td>
                            <td>{order["totalAmount"]}</td>
                        </tr>
                    )
                })}
                </tbody>
            </table>
            <ToastContainer></ToastContainer>
            </div>
            </>
        )
    }

    
}

export default ViewOrders;