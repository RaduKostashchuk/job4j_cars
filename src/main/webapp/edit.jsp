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
    <script src="js/edit.js?version=22"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-2">
            <ul class="nav justify-content-start">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>">
                        На главную
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-3 offset-7">
            <ul class="nav justify-content-end">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                        <c:out value="${driver.name}"/> | Выйти
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="card">
        <div class="card-header">
            <p class="h5">
                <c:if test="${param.id != null}">
                <div class="row">
                    <div class="col-4">
                        <p class="h5">Редактировать объявление</p>
                    </div>
                    <div class="col-3 offset-5">
                        <form action="<c:url value='/delete'/>" method="post" id="deleteForm">
                            <input type="hidden" name="advId" value="<c:out value='${param.id}'/>">
                            <input type="hidden" name="driverId" value="<c:out value='${driver.id}'/>">
                            <div class="col p-2">
                                <button type="submit" class="btn btn-warning">Удалить объявление</button>
                            </div>
                        </form>
                    </div>
                </div>
                </c:if>
                <c:if test="${param.id == null}">
                    Новое объявление
                </c:if>
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
                                      style="height: 100px" placeholder="Описание"></textarea>
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
                                <button type="submit" class="btn btn-primary">Сохранить</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form action="<c:url value='/upload?id=${param.id}'/>" method="post"
                  enctype="multipart/form-data" onsubmit="return validatePhoto()">
                <div class="row border m-1">
                    <p class="h6 m-1 fw-bold">Загрузка фотографии</p>
                    <div class="col-2 m-1">
                        <label for="filename" class="btn btn-primary">Выбрать фото</label>
                        <input type="file" name="file" id="filename" style="visibility:hidden;">
                    </div>
                    <div class="col-2 m-1">
                        <button type="submit" class="btn btn-primary">Загрузить фото</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <c:if test="${param.id != null}">
    <div class="card">
        <div class="card-header">
            <p class="h5">Просмотр объявленния</p>
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
                            <th class="table-light">Кузов</th>
                            <td id="body"></td>
                        </tr>
                        <tr>
                            <th class="table-light">Двигатель</th>
                            <td id="engine"></td>
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
        </div>
    </div>
    </c:if>
</div>
</body>
</html>