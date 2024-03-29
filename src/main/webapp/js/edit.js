function validatePhoto() {
    const filename = $('#filename').val();
    let alertMessage = "";
    if (filename === "") {
        alertMessage = "Необходимо выбрать файл";
    }
    if (alertMessage !== "") {
        alert(alertMessage);
        return false;
    }
    return true;
}
function validate() {
    const brandId = parseInt($('#brandSelect').val(), 10);
    const modelId = parseInt($('#modelSelect').val(), 10);
    const bodyId = parseInt($('#bodySelect').val(), 10);
    const engineId = parseInt($('#engineSelect').val(), 10);
    const drivetrainId = parseInt($('#dtSelect').val(), 10);
    const year = $('#yearInput').val();
    const mileage = $('#mileageInput').val();
    let alertMessage = "";
    if (brandId === 0) {
        alertMessage = "Необходимо выбрать марку\n";
    }
    if (modelId === 0) {
        alertMessage += "Необходимо выбрать модель\n";
    }
    if (bodyId === 0) {
        alertMessage += "Необходимо выбрать тип кузова\n";
    }
    if (engineId === 0) {
        alertMessage += "Необходимо выбрать тип двигателя\n";
    }
    if (drivetrainId === 0) {
        alertMessage += "Необходимо указать привод\n";
    }
    if (year === "") {
        alertMessage += "Необходимо указать год\n";
    }
    if (mileage === "") {
        alertMessage += "Необходимо указать пробег";
    }
    if (alertMessage !== "") {
        alert(alertMessage);
        return false;
    }
    return true;
}
function getBodies() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: context + '/bodies',
            dataType: 'json'
        }).done(function (data) {
            for (let body of data) {
                $('#bodySelect option:last').after(`<option value=${body.id}>${body.type}</option>`)
            }
        })
    });
}
function getEngines() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: context + '/engines',
            dataType: 'json'
        }).done(function (data) {
            for (let engine of data) {
                $('#engineSelect option:last').after(`<option value=${engine.id}>${engine.type}</option>`)
            }
        })
    });
}
function getDrivetrains() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: context + '/drivetrains',
            dataType: 'json'
        }).done(function (data) {
            for (let drivetrain of data) {
                $('#dtSelect option:last').after(`<option value=${drivetrain.id}>${drivetrain.type}</option>`)
            }
        })
    });
}
function getBrandModels() {
    let modelSelect = document.getElementById("modelSelect");
    while (modelSelect.firstChild) {
        modelSelect.removeChild(modelSelect.firstChild);
    }
    let firstOption = document.createElement("option");
    firstOption.selected = true;
    firstOption.innerText = "Модель";
    modelSelect.append(firstOption);
    let id = $( "#brandSelect option:selected" ).val();
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: context + '/models' + '?id=' + id,
            dataType: 'json'
        }).done(function (data) {
            for (let model of data) {
                $('#modelSelect option:last').after(`<option value=${model.id}>${model.name}</option>`)
            }
        })
    });
}
function addHiddenInput() {
    let id = getAdvId();
    $(document).ready(function () {
        let form = document.getElementById("editForm");
        let input = document.createElement("input");
        input.type = "hidden";
        input.name = "id";
        input.value = id;
        form.append(input);
    })
}
if (getAdvId() !== -1) {
    fillTable();
    addHiddenInput();
    placeImage();
}
getBodies();
getEngines();
getBrands();
getDrivetrains();
