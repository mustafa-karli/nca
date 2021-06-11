<%@ attribute name="tree" type="com.nauticana.basis.model.ViewTreeData" required="true"%>
<%@ attribute name="tagName" type="String" required="true"%>
<%@ attribute name="defvalue" type="String" required="true"%>
<%@ attribute name="modalTitle" type="String" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ncaTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="my-modal">
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-num="0">Question 1</button>
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-num="1">Question 2</button>

  <!-- Modal HTML -->
  <div id="myModal" class="modal fade">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Title</h4>
        </div>
        <div class="modal-body">

				<ncaTags:treeCheck tree="${t.children}" linkText="" linkAddr="" linkFunc="select${tagName}"/>

        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-primary" class="close" data-dismiss="modal" onclick="select${tagName}()">Submit</button>
        </div>
      </div>
    </div>
  </div>
  <hr/>
  <h4 id="selected"></h4>
</div>

<script>
function select${tagName}() {
	  var selected = $(".modal-body input:checked").val();
	  $("#${tagName}").text('You selected ' + selected);
	}

	function showTreeSelection(event, $modal) {
	  var button = $(event.relatedTarget);  // Button that triggered the modal
	  var num = parseInt(button.data('num'));
	  var question = questions[num];
	  $modal.find('.modal-title').text("${modalTitle}");
	  $modal.find('.modal-body').empty().append(getOptions(question));
	}

	$(function() {
		$("#myModal").on('show.bs.modal', function(event) {
	    showQuestion(event, $(this));
	  });
	});

</script>