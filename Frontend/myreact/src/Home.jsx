import React, { useState } from 'react';
import { Carousel } from 'react-bootstrap';
import GetServerUrl from './GetServerUrl';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

function Home(){
    const navigate = useNavigate();
    const serverUrl = GetServerUrl();
    const imgStyle = {"widht":"100%", "height":"400px"};
    const [search, setSearch] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResult, setSearchResult] = useState([]);
    const currentUserId = sessionStorage.getItem("id");
    const currentUserName = sessionStorage.getItem("name");

    const sendSearchPayload = async()=>{
        try{
            const resp = await axios.post(`${serverUrl}/common/searchMedicines`, {"search":searchTerm});
            if(resp.status === 200){
                setSearchResult(resp.data.body);
                setSearch(true);
            }
        }
        catch(ex){
            toast.error("No results found");
        }
    }

    const viewMedicine = async(id)=>{
        if(currentUserId != null)
            navigate("/viewMedicine", {state:{"id":id}});
        else
            toast.error("please login first");
    }



    return (
        <>
            <div className="container">
                {(currentUserId != null) && 
                    <h4 style={{"textAlign":"center"}} className='mt-3'>
                        Welcome, {currentUserName}
                    </h4>
                }
                <div class="input-group mb-3 mt-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Search</span>
                </div>
                <input type="text" value={searchTerm} onChange={(e)=>{setSearchTerm(e.target.value)}} class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"/>
                <button className="btn btn-primary" onClick={sendSearchPayload}>Search now</button>
                </div>
                {
                    !search && 
                    <div className="container" style={{"textAlign":"center"}}>
                        <div className='' style={{"textAlign":"center", "width":"45%", "display":"inline-block", "margin":"15px"}}>
                            <Carousel data-bs-theme="dark">
                                <Carousel.Item>
                                    <img
                                    className="d-block"
                                    src="img1.png"
                                    alt="First slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img
                                    className="d-block"
                                    src="img2.png"
                                    alt="Second slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img
                                    className="d-block w-100"
                                    src="img4.jpg"
                                    alt="Third slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img
                                    className="d-block w-100"
                                    src="img5.jpg"
                                    alt="Third slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                            </Carousel>
                        </div>
                        <div className='' style={{"textAlign":"center", "width":"45%", "display":"inline-block"}}>
                            <Carousel data-bs-theme="dark">
                                <Carousel.Item>
                                    <img
                                    className="d-block"
                                    src="img1.png"
                                    alt="First slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img
                                    className="d-block"
                                    src="img2.png"
                                    alt="Second slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img
                                    className="d-block w-100"
                                    src="img4.jpg"
                                    alt="Third slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                                <Carousel.Item>
                                    <img
                                    className="d-block w-100"
                                    src="img5.jpg"
                                    alt="Third slide"
                                    style={imgStyle}
                                    />
                                </Carousel.Item>
                            </Carousel>
                        </div>
                    </div>
                }
                {
                    search && 
                    <>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Medicine Name</th>
                                    <th scope="col">Medicine Type</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                        {searchResult.map((result)=>{
                            return(
                                <>
                                    <tr>
                                        <td>{result["name"]}</td>
                                        <td>{result["type"]}</td>
                                        <td style={{"textAlign":'center'}}> 
                                            <button  className="btn btn-success" onClick={()=>{viewMedicine(result["id"])}}>
                                                View Medicine
                                            </button>
                                        </td>
                                    </tr>
                                </>
                            )
                        })}
                            </tbody>
                        </table>
                    </>
                }
            </div>
            <ToastContainer></ToastContainer>
        </>
    )
}

export default Home;