import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useSearchParams } from "react-router-dom"
import GetServerUrl from "./GetServerUrl";
import { ToastContainer, toast } from "react-toastify";

function ViewMedicine(){
    const currentUserId = sessionStorage.getItem("id");
    const currentUserName = sessionStorage.getItem("name");
    const serverUrl = GetServerUrl();
    const medicineId = useLocation().state.id;
    const [searchResult, setSearchResult] = useState([]);
    const [quantity, setQuantity] = useState([]);
    const getMedicine = async ()=>{
        try{
            const resp = await axios.get(`${serverUrl}/common/viewMedicine/${medicineId}`);
            if(resp.status === 200){
                setSearchResult(resp.data);
                for(let i = 0; i < resp.data.length; i++){
                    quantity[i] = 1;
                }
            }
        }
        catch(ex){
            toast.error("Something went wrong");
        }
    }


    useEffect(()=>{
        getMedicine();
    }, [])


    const changeQuantity = (e, medicineId, distributorId)=>{
        for(let i = 0; i < quantity.length; i++){
            console.log(quantity);
            if(searchResult[i]["medicineId"] === medicineId && searchResult[i]["distributorId"] === distributorId && e.target.value > 0) {
                console.log("inside if");
                const temp = [...quantity];
                temp[i] = e.target.value;
                setQuantity(temp);
                console.log(quantity);
            }
        }
    }

    const buyMedicine = async(medicineId, distributorId, quantity)=>{
        try{
            const payload = {"medicineId":medicineId, "distributorId":distributorId, "pharmacistId":parseInt(currentUserId), "quantity":parseInt(quantity)};
            console.log(payload);
            const resp = await axios.post(`${serverUrl}/pharmacist/buyMedicine`, payload);
            if(resp.status === 201){
                toast("medicines bought successfully");
                toast("you can view it in orders page");
            }
            else{
                toast.error("failed");
            }
        }
        catch(ex){
            toast.error("failed");
        }
    }


    return (
        <>
            <div className="container">
                <h4 style={{"textAlign":"center"}}>Welcome, {currentUserName}</h4>
            <table className="table mt-3">
                <thead>
                    <tr>
                        <th scope="col">Medicine Name</th>
                        <th scope="col">Distributor Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                {searchResult.map((result, index)=>{
                    return(
                        <tr key={index}>
                            <td>{result["medicineName"]}</td>
                            <td>{result["distributorName"]}</td>
                            <td>{result["price"]}</td>
                            <td>
                                Select Quantity:{" "}
                                <input value={quantity[index]} onChange={(e)=>{
                                    changeQuantity(e, result["medicineId"], result["distributorId"]);
                                }} type="number" />
                            </td>
                            <td>{result["price"]*quantity[index]}</td>
                            <td style={{"textAlign":'center'}}> 
                                <button  className="btn btn-success" onClick={()=>{buyMedicine(result["medicineId"], result["distributorId"], quantity[index])}}>
                                    Buy Medicine
                                </button>
                            </td>
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

export default ViewMedicine;