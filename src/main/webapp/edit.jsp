<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование объявленния</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
    <script>
        let context = "http://" + "<%=request.getServerName()%>"
            + ":" + "<%=request.getServerPort()%>"
            + "<%=request.getServletContext().getContextPath()%>"
    </script>
    <script src="js/lib.js?version=5"></script>
    <script src="js/edit.js?version=2"></script>
</head>
<body>
    <div class="my-navbar bg-info">
        <div class="row">
            <div class="col-4">
                <p class="h5 text-center m-2">Autosale.ru</p>
            </div>
            <div class="col-2 offset-2">
                <a class="btn btn-primary my-1" href="<%=request.getContextPath()%>">
                    На главную
                </a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary my-1" href="<%=request.getContextPath()%>/edit.jsp">
                    Добавить объявление
                </a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary my-1" href="<%=request.getContextPath()%>/logout">
                    <c:out value="${driver.name}"/> | Выйти
                </a>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header">
            <p class="h6">
                <c:if test="${param.id != null}">
                    Редактировать объявление
                </c:if>
                <c:if test="${param.id == null}">
                    Новое объявление
                </c:if>
            </p>
        </div>
        <div class="card-body form-group">
            <c:if test="${param.id != null}">
                <form action="<c:url value='/edit'/>" method="post" id="editForm">
            </c:if>
            <c:if test="${param.id == null}">
                <form action="<c:url value='/add'/>" method="post" id="editForm" onsubmit="return validate()">
            </c:if>
                <div class="row border m-1">
                    <div class="col">
                        <p class="h6 m-1 fw-bold">Информация о машине</p>
                        <div class="form-floating">
                            <textarea class="form-control m-1" id="descriptionInput" name="description"
                                      style="height: 150px" placeholder="Описание"></textarea>
                        </div>
                    </div>
                    <div class="col">
                        <div class="row row-cols-3">
                            <div class="col p-2">
                                <select class="custom-select" id="brandSelect" name="brand" onchange="getBrandModels()">
                                    <option value="0" selected>Марка</option>
                                </select>
                            </div>
                            <div class="col p-2">
                                <select class="custom-select" id="modelSelect" name="model">
                                    <option value="0" selected>Модель</option>
                                </select>
                            </div>
                            <div class="col p-2">
                                <label for="priceInput" class="form-text-label">Цена</label>
                                <input type="number" min="0" max="10000000" id="priceInput" size="8" class="form-text-input" name="price">
                            </div>
                            <div class="col p-2">
                                <select class="custom-select" id="bodySelect" name="body">
                                    <option value="0" selected>Кузов</option>
                                </select>
                            </div>
                            <div class="col p-2">
                                <select class="custom-select" id="engineSelect" name="engine">
                                    <option value="0" selected>Двигатель</option>
                                </select>
                            </div>
                            <div class="col p-2">
                                <label for="soldCheckbox" class="form-check-label">Продана</label>
                                <input type="checkbox" id="soldCheckbox" class="form-check-input" name="sold">
                            </div>
                            <div class="col p-2">
                                <select class="custom-select" id="dtSelect" name="drivetrain">
                                    <option value="0" selected>Привод</option>
                                </select>
                            </div>
                            <div class="col p-2">
                                <label for="yearInput" class="form-text-label">Год</label>
                                <input type="number" min="1980" max="2022" id="yearInput" size="5" class="form-text-input" name="year">
                            </div>
                            <div class="col p-2">
                                <label for="mileageInput" class="form-text-label">Пробег</label>
                                <input type="number" min="0" max="999999" id="mileageInput" size="7" class="form-text-input" name="mileage">
                            </div>
                            <div class="col p-2">
                                <button type="submit" class="btn btn-primary">Сохранить</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
                <div class="row">
                    <div class="col-6">
                        <form action="<c:url value='/upload?id=${param.id}'/>" method="post"
                              enctype="multipart/form-data" onsubmit="return validatePhoto()">
                            <div class="row border m-1">
                                <p class="h6 m-1 fw-bold">Загрузка фотографии</p>
                                <div class="col-4 m-1">
                                    <label for="filename" class="btn btn-primary">Выбрать фото</label>
                                    <input type="file" name="file" id="filename" style="visibility:hidden;">
                                </div>
                                <div class="col-4 m-1">
                                    <button type="submit" class="btn btn-primary">Загрузить фото</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <c:if test="${param.id != null}">
                    <div class="col-4">
                        <form action="<c:url value='/delete'/>" method="post" id="deleteForm">
                            <input type="hidden" name="advId" value="<c:out value='${param.id}'/>">
                            <input type="hidden" name="driverId" value="<c:out value='${driver.id}'/>">
                            <div class="col p-2">
                                <button type="submit" class="btn btn-warning">Удалить объявление</button>
                            </div>
                        </form>
                    </div>
                    </c:if>
                </div>
        </div>
    </div>
    <c:if test="${param.id != null}">
    <div class="card">
        <div class="card-header">
            <p class="h6">Просмотр объявленния</p>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-4">
                    <table class="table table-bordered m-1">
                        <thead class="table-light">
                        <tr>
                            <th colspan="2" class="text-center">Характеристики</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th class="table-light">Марка</th>
                            <td id="brand"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Модель</th>
                            <td id="model"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Год</th>
                            <td id="year"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Цена</th>
                            <td id="price"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Пробег</th>
                            <td id="mileage"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Кузов</th>
                            <td id="body"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Двигатель</th>
                            <td id="engine"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Привод</th>
                            <td id="drivetrain"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Опубликовано</th>
                            <td id="created"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Статус</th>
                            <td id="sold"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-6" id="image"></div>
            </div>
            <div class="row">
                <div class="col-8">
                    <table class="table table-bordered m-1">
                        <thead class="table-light">
                        <tr>
                            <th colspan="2" class="text-center">Описание</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><p id="description" class="m-1"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-4">
                    <table class="table table-bordered m-1">
                        <thead class="table-light">
                        <tr>
                            <th colspan="2" class="text-center">Продавец</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><p id="ownerName" class="m-1"></td>
                        </tr>
                        <tr>
                            <td><p id="ownerPhone" class="m-1"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</body>
</html>