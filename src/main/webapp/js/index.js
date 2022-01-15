function getAdvertisements() {
    return $.ajax({
        type: 'GET',
        url: context + '/advertisements',
        dataType: 'json'
    })
}
function compare(a, b) {
    return a.created.localeCompare(b.created);
}
function drawTable() {
    window.onload = getAdvertisements().done(function (data) {
        let allCheckBox = document.getElementById("allCheck");
        let myCheckBox = document.getElementById("myCheck");
        data.sort(compare);
        let tbody = document.getElementById("mainTableBody");
        while (tbody.firstChild) {
            tbody.removeChild(tbody.firstChild);
        }
        for(let advertisement of data) {
            if (!allCheckBox.checked && advertisement.sold) {
                continue;
            }
            if (advertisement.driver.id !== driverId && myCheckBox !== null && myCheckBox.checked) {
                continue;
            }
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            let td3 = document.createElement("td");
            let td4 = document.createElement("td");
            let img = document.createElement("img");
            let a = document.createElement("a");
            if (driverId === -1 || advertisement.driver.id !== driverId) {
                a.href = context + '/advertisement.jsp?id=' + advertisement.id;
            } else {
                a.href = context + '/edit.jsp?id=' + advertisement.id;
            }
            img.src = context + '/download?id=' + advertisement.id;
            img.width = 120;
            img.alt = advertisement.car.brand.name + ' ' + advertisement.car.model.name;
            a.append(img);
            td1.append(a);
            td2.innerText = advertisement.car.brand.name;
            td3.innerText = advertisement.car.model.name;
            td4.innerHTML = '<h5><span class="badge rounded-pill bg-success text-dark">В продаже</span></h5>';
            if (advertisement.sold) {
                td4.innerHTML = '<h5><span class="badge rounded-pill bg-warning text-dark">Продана</span></h5>';
            }
            tr.append(td1);
            tr.append(td2);
            tr.append(td3);
            tr.append(td4);
            tbody.append(tr);
        }
    })
}
drawTable();
setInterval(drawTable, 30000);

