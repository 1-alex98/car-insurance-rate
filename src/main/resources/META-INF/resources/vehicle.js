var vehicleData= [];
let vehicleTypeInput = document.getElementById("vehicleType");
const typeOptions = vehicleTypeInput.options;
const brandInput = document.getElementById("vehicleBrand");
const brandOptions = brandInput.options;

function htmlEncode(str) {
    const el = document.createElement('div');
    el.appendChild(document.createTextNode(str));
    return el.innerHTML;
}

function loadVehicleTypes()
{
    let selectedBrand = brandInput.value;
    removeOptions(vehicleTypeInput);
    vehicleData.filter(value => value["brand"] === selectedBrand)
        .forEach(value => typeOptions.add(createTypeOption(value["name"], value["id"])))
}

function createTypeOption(type, id) {
    let optionElement = document.createElement("option");
    optionElement.text = htmlEncode(type);
    optionElement.value = htmlEncode(id);
    return optionElement;
}

function createBrandOption(brand) {
    let optionElement = document.createElement("option");
    optionElement.text = htmlEncode(brand);
    optionElement.value = htmlEncode(brand);
    return optionElement;
}

function setSelectVehicleOptions(data) {
    for(const brand of [...new Set(data.map(vehicle => vehicle["brand"]))]){
        brandOptions.add(createBrandOption(brand))
    }
    loadVehicleTypes()
}

fetch("/car-type")
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
    })
    .then(data => {
        vehicleData = data;
        setSelectVehicleOptions(data);
    })
    .catch(e => {
        error("There has been a problem with your getting the car types:"+ e);
        console.error("There has been a problem with your fetch operation:", e);
    });