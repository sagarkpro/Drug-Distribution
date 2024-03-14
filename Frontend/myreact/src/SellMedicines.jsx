import axios from "axios";
import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import GetServerUrl from "./GetServerUrl";

function SellMedicines(){
    const serverUrl = GetServerUrl();
    const currentUserId = sessionStorage.getItem("id");
    const currentUserName = sessionStorage.getItem("name");
    const currentUserType = sessionStorage.getItem("userType");
    const [searchTerm, setSearchTerm] = useState("");
    const [searchResult, setSearchResult] = useState([]);
    const [price, setPrice] = useState([]);
    
    useEffect(()=>{
        if(currentUserId == null || currentUserType !== 'Distributor' || currentUserType == null){
            console.log("inside if");
            toast.error("Please login as distributor");
            
        }
    }, [])

    const bugFix = async (data)=>{
        setSearchResult(data);
        console.log("RESULT:");
        console.log(searchResult);
        let arr=[];
        for(let i = 0; i < data.length; i++){
            arr.push(0);
        }
        console.log(arr);
        setPrice(arr);
        console.log("Price: ");
        console.log(price);
    }

    const sendSearchPayload = async()=>{
        try{
            const resp = await axios.post(`${serverUrl}/common/searchMedicines`, {"search":searchTerm});
            if(resp.status === 200){
                await bugFix(resp.data.body);
            }
        }
        catch(ex){
            toast.error("No results found");
        }
    }

    const sendSellPayload = async(medicineId, myprice)=>{
        try{
            const resp = await axios.post(`${serverUrl}/distributor/sellMedicine`, {
                "medicineId":medicineId,
                "distributorId":currentUserId,
                "price": parseFloat(myprice)
            });
            if(resp.status === 201){
                toast("Sucess!");
            }
            else{
                toast.error("Something went wrong");
            }
        }
        catch(ex){
            toast.error("Something went wrong");
        }
    }





    return (
        <>
            {currentUserId != null &&
                <div className="container">
                    <h4 style={{"textAlign":"center"}} className='mt-3'>
                        Welcome, {currentUserName}
                    </h4>
                    <div className ="input-group mb-3 mt-3">
                    <div className ="input-group-prepend">
                        <span className ="input-group-text" id="inputGroup-sizing-default">Search</span>
                    </div>
                    <input type="text" value={searchTerm} onChange={(e)=>{setSearchTerm(e.target.value)}} className="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"/>
                    <button className="btn btn-primary" onClick={sendSearchPayload}>Search now</button>
                    </div>
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">Medicine Name</th>
                                <th scope="col">Medicine Type</th>
                                <th scope="col">Set Price</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        {searchResult.map((result, index)=>{
                            return(
                                    <tr key={index}>
                                        <td>{result["name"]}</td>
                                        <td>{result["type"]}</td>
                                        <td>
                                            <input type="number" value={price[index]} onChange={(e)=>{
                                                let temp = [...price];
                                                for(let i = 0; i < temp.length; i++){
                                                    if(searchResult[i]["name"] === result["name"]){
                                                        temp[i] = e.target.value;
                                                    }
                                                }
                                                setPrice(temp);
                                                }} />
                                        </td>
                                        <td style={{"textAlign":'center'}}> 
                                            <button  className="btn btn-success" onClick={()=>{sendSellPayload(result["id"], price[index])}}>
                                                Sell
                                            </button>
                                        </td>
                                    </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>
                
            }
            <ToastContainer></ToastContainer>
        </>
    );
}

export default SellMedicines;