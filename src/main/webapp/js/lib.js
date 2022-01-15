function getAdvId() {
    let params = new URLSearchParams(document.location.search);
    let id = params.get("id");
    return id === null ? -1 : id;
}
function placeImage() {
    window.onload = function () {
        let id = getAdvId();
        let container = document.getElementById("image");
        let img = document.createElement("img");
        img.src = context + '/download?id=' + id;
        img.alt = 'no image available';
        img.className = 'img-fluid m-1';
        container.append(img);
    }
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
            let createdTd = document.getElementById("created");
            let soldTd = document.getElementById("sold");
            let descP = document.getElementById("description");
            brandTd.innerText = advertisement.car.brand.name;
            modelTd.innerText = advertisement.car.model.name;
            bodyTd.innerText = advertisement.car.body.type;
            engineTd.innerText = advertisement.car.engine.type;
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