function rate(){
    let kilometersVal = document.getElementById("kilometersInput").value;
    let carVal = document.getElementById("vehicleType").value;
    let locationVal = document.getElementById("locationInput").value;

    let searchParams = new URLSearchParams({
        "carTypeId": carVal,
        "locationId": locationVal,
        "yearlyKilometersDriven": kilometersVal
    });

    fetch(`/rate?${searchParams}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("rateWrapper").classList.remove("d-none");
            document.getElementById("rate").innerText = data["rate"];
        })
        .catch(e => {
            error("Could not load rate");
            document.getElementById("rateWrapper").classList.add("d-none");
            console.error(e);
        });
}