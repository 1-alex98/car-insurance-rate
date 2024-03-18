function error(message){
    document.getElementById("error").classList.remove("d-none");
    document.getElementById("error").innerText = message;
}

function removeOptions(selectElement) {
    let i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
        selectElement.remove(i);
    }
}

function createLocationOptionOfData(option) {
    let optionElement = document.createElement("option");
    optionElement.text = htmlEncode(`${option["region1"]} ${option["region3"]} ${option["region4"]} ${option["location"]}`);
    optionElement.value = htmlEncode(option["id"]);
    return optionElement;
}

function setSelectOptions(data) {
    let locationInput = document.getElementById("locationInput");
    removeOptions(locationInput)
    if(data.length === 0){
        //TODO:show user there is nothing found
        return;
    }
    for(const option of data){
        let optionElement = createLocationOptionOfData(option);
        locationInput.options.add(optionElement);
    }
}

function resetError() {
    document.getElementById("error").classList.add("d-none");
}
function isNumeric(input) {
    const regExp = /^[0-9]+$/;
    return regExp.test(input);
}
function loadPlace(){
    let zip = document.getElementById("zipInput").value;
    if(zip === ""){
        return
    }
    if(!isNumeric(zip)){
        error("Only numbers in zip code allowed")
        return;
    }
    fetch(`/place?plz=${zip}`)
        .then(response => {
            if (!response.ok) {
                error("unexpected error");
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            setSelectOptions(data);
            resetError();
        })
        .catch(e => {
            error("There has been a problem with getting locations:"+ e);
            console.error("There has been a problem with your fetch operation:", e);
        });
}