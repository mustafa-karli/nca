$(function () {
  $("td").dblclick(function () {
    var OriginalContent = $(this).text();
    $(this).addClass("cellEditing");
    $(this).html("<input type='text' value='" + OriginalContent + "' />");
    $(this).children().first().focus();
    $(this).children().first().keypress(function (e) {
      if (e.which == 13) {
        var newContent = $(this).val();
        $(this).parent().text(newContent);
        $(this).parent().removeClass("cellEditing");
      }
    });
    $(this).children().first().blur(function(){
      $(this).parent().text(OriginalContent);
      $(this).parent().removeClass("cellEditing");
    });
  });
});


function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        csv.push(row.join(","));        
    }
    downloadCSV(csv.join("\n"), filename);
}

function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    csvFile = new Blob([csv], {type: "text/csv"});
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);
    downloadLink.click();
}

function exportExcel(table_id){
    var blob = new Blob([document.getElementById(table_id).innerHTML], {
        type: "text/plain;charset=utf-8;"
    });
    saveAs(blob, "tableExport.xls");
}

function showMenu() {
  if (  document.getElementById("accordion").style.display == "block") {
    document.getElementById("accordion").style.display = "none";
    document.getElementById("content").style.left = "0";
  } else {
    document.getElementById("accordion").style.display = "block";
    document.getElementById("content").style.left = "200px";
  }
}

function doAjaxGet(target) {
  $.ajax({
     type: "GET",
     url : target,
     success : function( response ) {
        $("#content").html( response );
     }
  });
}

function doAjaxDelete(target,row) {
	  $.ajax({
	     type: "DELETE",
	     url : target,
	     success : function( response ) {
	    	 row.remove();
	    	 return response;
	     }
	  });
	}

function doAjaxPost(target) {
	var stop = 0;
	$('input.required').each(function(){
	if($(this).val() == '') {
		$(this).addClass('requiredFalse');
		
//		css({
//			'border-color':'blue', 
//			'border-style': 'solid',
//	    	'border-width' : 'thin'
//		});
		stop=1;
	} else
		$(this).removeClass('requiredFalse');
//		css({'border-color':''});
	});

	if(stop == 0){
		$.ajax({
			type: "POST",
			url : target,
			data: $("#f").serialize(),
			success : function( response ) {
				$("#content").html( response );
			}
		});
	};
}

function doAjaxBinPost(target, formName, type) {
	var formData=new FormData($(formName)[0]);
	debugger;
	$.ajax({
		type: type,
		url : target,
		data: formData,
		processData: false,
		contentType: false,
		cache: false,
		timeout: 600000,
		success : function(response) {

			var img = response;
			var tmb = img.replace('read','thumb');
	
			updateHtml(img,tmb);
			$("#binContent").text(  response );
			console.log("SUCCESS : ", formData);
			$('#btnSubmit').prop("disabled", false);
		},
		error : function(e) {
			$("#binContent").text(e.responseText);
			console.log("ERROR : ", e);
			$('#btnSubmit').prop("disabled", false);
		}
	});
}

function selectAll(cname) {
  var checkBoxes = document.getElementsByName(cname);
  for (var i in checkBoxes) {
    checkBoxes[i].checked = true;
  }
}

function deSelectAll(cname) {
  var checkBoxes = document.getElementsByName(cname);
  for (var i in checkBoxes) {
    checkBoxes[i].checked = false;
  }
}

function toggleSelect(cname) {
  var checkBoxes = document.getElementsByName(cname);
  for (var i in checkBoxes) {
    checkBoxes[i].checked = !checkBoxes[i].checked;
  }
}

function format_date(date,type) {
	var formatedDate = new Date(date);
	  month=formatedDate.getMonth();
	  month=month+1; //javascript date goes from 0 to 11
	  if (month<10) month="0"+month; //adding the prefix

	  year=formatedDate.getFullYear();
	  day=formatedDate.getDate();
	  hour=formatedDate.getHours();
	  minutes=formatedDate.getMinutes();
	  seconds=formatedDate.getSeconds();
	  
	if (type=="D")
		return day+"/"+month+"/"+year;
	else if(type=="F") 
		return day+"/"+month+"/"+year+" "+hour+":"+minutes;
}

function resetSelectOptions(sname, addr, required) {
	var slct = document.getElementById(sname);
	if (slct) {
		while (slct.options.length) slct.remove(0);
		if (!(required)) slct.options.add(new Option('',''));
		$.getJSON(addr, function(data) {
			$.each(data, function() {
				slct.options.add(new Option(this.txt, this.key));
			});
		});
	}
}
