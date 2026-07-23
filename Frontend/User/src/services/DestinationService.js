import axios from "axios";

const API = "http://localhost:8080/api/destinations";

export const getAllDestinations = () => {
    return axios.get(API);
};

export const getDestinationById = (id) => {
    return axios.get(`${API}/${id}`);
};

export const addDestination = (destination) => {
    return axios.post(API, destination);
};

export const updateDestination = (id, destination) => {
    return axios.put(`${API}/${id}`, destination);
};

export const deleteDestination = (id) => {
    return axios.delete(`${API}/${id}`);
};