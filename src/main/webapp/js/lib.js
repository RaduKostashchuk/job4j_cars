function getAdvId() {
    let params = new URLSearchParams(document.location.search);
    let id = params.get("id");
    return id === null ? -1 : id;
}
function placeImage() {
    $(document).ready(function () {
        let id = getAdvId();
        let container = document.getElementById("image");
        let img = document.createElement("img");
        img.src = context + '/download?id=' + id;
        img.alt = 'no image available';
        img.className = 'img-fluid m-1';
        container.append(img);
    })
}
function getAdvertisement() {
    let id = getAdvId();
    return $.ajax({
        type: 'GET',
        url: context + '/advertisement?id=' + id,
        dataType: 'json'
    })
}
function fillTable() {
    $(document).ready(function () {
        getAdvertisement().done(function (advertisement) {
            let brandTd = document.getElementById("brand");
            let modelTd = document.getElementById("model");
            let bodyTd = document.getElementById("body");
            let engineTd = document.getElementById("engine");
            let yearTd = document.getElementById("year");
            let mileageTd = document.getElementById("mileage");
            let drivetrainTd = document.getElementById("drivetrain");
            let createdTd = document.getElementById("created");
            let soldTd = document.getElementById("sold");
            let descP = document.getElementById("description");
            let ownerNameTd = document.getElementById("ownerName");
            let ownerPhoneTd = document.getElementById("ownerPhone");
            let priceTd = document.getElementById("price");
            brandTd.innerText = advertisement.car.brand.name;
            modelTd.innerText = advertisement.car.model.name;
            bodyTd.innerText = advertisement.car.body.type;
            engineTd.innerText = advertisement.car.engine.type;
            yearTd.innerText = advertisement.car.year;
            mileageTd.innerText = advertisement.car.mileage;
            drivetrainTd.innerText = advertisement.car.drivetrain.type;
            ownerNameTd.innerText = advertisement.driver.name;
            ownerPhoneTd.innerText = advertisement.driver.phone;
            priceTd.innerText = advertisement.price;
            const date = new Date(advertisement.created);
            const options = { weekday: 'short', year: 'numeric', month: 'short', day: 'numeric',
                hour: 'numeric', minute: 'numeric'};
            createdTd.innerText = date.toLocaleDateString("ru-RU", options);
            soldTd.innerText = 'В продаже';
            if (advertisement.sold) {
                soldTd.innerText = 'Продана';
            }
            descP.innerText = advertisement.description;
        })
    })
}
function getBrands() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: context + '/brands',
            dataType: 'json'
        }).done(function (data) {
            for (let brand of data) {
                $('#brandSelect option:last').after(`<option value=${brand.id}>${brand.name}</option>`)
            }
        })
    });
}