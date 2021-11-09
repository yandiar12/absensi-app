import axios from "axios";

export default axios.create({
  baseURL: "https://rest-api-absensi.herokuapp.com/api",
  headers: {
    "Content-type": "application/json"
  }
});