<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Просмотр объявленния</title>

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
    <script src="js/lib.js?version=7"></script>
    <script src="js/advertisement.js?version=7"></script>
</head>
<body>
<div class="my-navbar bg-info">
    <div class="row">
        <div class="col-4">
            <p class="h5 text-center m-2">Autosale.ru</p>
        </div>
        <div class="col-2 offset-2">
            <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>">
                На главную
            </a>
        </div>
        <c:if test="${driver != null}">
            <div class="col-2">
                <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/edit.jsp">
                    Добавить объявление
                </a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/logout">
                    <c:out value="${driver.name}"/> | Выйти
                </a>
            </div>
        </c:if>
        <c:if test="${driver == null}">
            <div class="col-2">
                <a class="btn btn-primary disabled m-1" href="<%=request.getContextPath()%>/edit.jsp" >
                    Добавить объявление
                </a>
            </div>
            <div class="col-2">
                <a class="btn btn-primary m-1" href="<%=request.getContextPath()%>/login.jsp">
                    Войти
                </a>
            </div>
        </c:if>
    </div>
</div>
<div class="container">
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
</body>
</html>