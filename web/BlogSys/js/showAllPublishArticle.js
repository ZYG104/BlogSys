// window.onload=function () {
//     var start = 0;
//     var max = 10;
//
//     var pageInfo = {}
//
//     pageInfo.start = start;
//     pageInfo.max = max;
//
//     $.ajax({
//         type:"POST",
//         url:"article/showAllPublishArticle.do",
//         data:JSON.stringify(pageInfo),
//         contentType: "application/json; charset=utf-8",
//         dataType:'json',
//         success:function (data) {
//             return data;
//         },
//         error:function (data) {
//             alert("没数据");
//             return false;
//         }
//     });
// }
