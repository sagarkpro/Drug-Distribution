function GetServerUrl(){
    const serverIp = "localhost";
    const serverPort = 8080;
    return `http://${serverIp}:${serverPort}`;
}

export default GetServerUrl;