<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/6/29
  Time: 0:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <title>药房工作站</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="breadcrumb">
                <li><a href="#">主页</a></li>
                <li class="active">药房工作站</li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-10 column">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#example-navbar-collapse">
                            <span class="sr-only">切换导航</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">药房工作站</a>

                    </div>
                    <div class="collapse navbar-collapse" id="example-navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li ><a href="pharmacy/drugManagement">药品信息维护</a></li>
                            <li class="active"><a href="pharmacy/deliverDrug">发药</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="col-md-2" >
            <input type='text' id='doctorId' class='form-control'/>
        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-3 column">
            <form role="form">
                <label class="col-md-4 control-label text-right" for="medical-record-no">病历号</label>
                <div class="col-md-8 form-group" >
                    <input type="text" class="form-control" id="medical-record-no" name="medical-record-no"/>
                </div>

                <label class="col-md-4 control-label text-right" for="state">状态</label>
                <div class="col-md-8 form-group" >
                    <select data-am-selected class="form-control"id = "state" name="state">
                        <option value="1" selected >未发</option>
                        <option value="2"  >已退</option>
                        <option value="3">已发</option>
                    </select>
                </div>
                <button class="btn btn-default" type="button" id="searchBy">search</button>
            </form>
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>病历号</th>
                    <th>姓名</th>
                    <th>id</th>
                </tr>
                </thead>
                <tbody id="prescriptionTable">
                </tbody>
            </table>
        </div>
        <div class="col-md-9 column">
        </div>
    </div>
</div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
    var patientName="";
    var prescriptionList="";
    //根据病历号在挂号表中寻找姓名
    // $.ajax({
    //     type: "POST",
    //     url: "pharmacy/"
    // });

    function findName(){
        $.ajax({
            type: "POST",
            url: "pharmacy/findNameByMedical",
            data: {medicalNo: $("#medical-record-no").val()},
            success: function (result) {
                patientName=result;
            },
            error : function(){
                alert("find name from registration fail");
            }
        });
    }
    function setPrescriptionTable(){
        var str;
        str = "";
        $("#prescriptionTable").html("");
        for (var i = 0;i <prescriptionList.length;i++){
            str +="<tr>\n" +
                "<td><input type=\"text\" class=\"form-control\" value=\""+prescriptionList[i].medicalRecordNo+"\" name=\"\" id=\"foundMedicalNo\"/></td>\n" +
                "<td><input type=\"text\" class=\"form-control\" value=\""+patientName+"\" name=\"\" id=\"foundName\"/></td>\n" +
                "<td><input type=\"text\" class=\"form-control\" value=\""+prescriptionList[i].takeMedicineState+"\" name=\"\" id=\"foundId\"/></td>\n" +
                "</tr>"
        }
        $("#prescriptionTable").append(str);
    }
    $(document).ready(function () {
        //searchBy按钮
        $("#searchBy").click(function(){
            $.ajax({
                type: "POST",
                url: "pharmacy/findAllDrugPrescriptionByMedical",
                data: {medicalNo: $("#medical-record-no").val(),state: $("#")},
                success: function (result){
                    prescriptionList=result;
                    findName();
                    setPrescriptionTable();
                },
                error:function(){
                    alert("findAllfail");
                }
            });
        });
    });
</script>
</body>
</html>
