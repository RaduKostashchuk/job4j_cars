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
</head>
<body>
<div class="container">
    <div class="row">
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <c:if test="${driver != null}">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                        <c:out value="${driver.name}"/> | Выйти</a>
                </c:if>
                <c:if test="${driver == null}">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </c:if>
            </li>
        </ul>
    </div>

    <div class="card">
        <div class="card-header">
            <p class="h5">Редактировать объявление</p>
        </div>
        <div class="card-body form-group">
            <form action="<%=request.getContextPath()%>/add" method="post" onsubmit="return validate()">
                <div class="row border m-1">
                    <p class="h6 m-1">Создание объявления</p>
                    <div class="col-md-8 m-1">
                        <input type="text" class="form-control" id="descriptionInput" name="description" placeholder="Описание">
                    </div>
                    <div class="col-md-1 m-1">
                        <select class="custom-select" id="brandSelect" name="brandId" onchange="getBrandModels()">
                            <option selected>Марка</option>
                        </select>
                    </div>
                    <div class="col-md-1 m-1">
                        <select class="custom-select" id="modelSelect" name="modelId">
                            <option selected>Модель</option>
                        </select>
                    </div>
                    <div class="col-md-1 m-1">
                        <select class="custom-select" id="bodySelect" name="bodyId">
                            <option selected>Кузов</option>
                        </select>
                    </div>
                    <div class="col-md-1 m-1">
                        <select class="custom-select" id="engineSelect" name="engineId">
                            <option selected>Двигатель</option>
                        </select>
                    </div>
                    <div class="col-md-1 m-1">
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                    </div>
                </div>
            </form>
            <form action="<%=request.getContextPath()%>/upload" method="post"
                  enctype="multipart/form-data" onsubmit="return validate()">
                <div class="row border m-1">
                    <p class="h6 m-1">Загрузка фотографии для объявления</p>
                    <div class="col-md-2 m-1">
                        <select class="custom-select" id="advSelect" name="advId">
                            <option selected>Объявление</option>
                        </select>
                    </div>
                    <div class="col-md-2 m-1">
                        <label for="filename" class="btn btn-primary">Выбрать фото</label>
                        <input type="file" name="file" id="filename" style="visibility:hidden;">
                    </div>
                    <div class="col-md-2 m-1">
                        <button type="submit" class="btn btn-primary">Загрузить фото</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>