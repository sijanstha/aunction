$(document).ready(
		function() {
			var itemId = 0;
			var error_bid_amount = false;
			var error_message = false;

			$('button[class^="btn btn-primary btn-bid"]').on(
					'click',
					function(event) {
						event.preventDefault();
						itemId = $(this).data('id');
						$("#itemId").val(itemId);
						// ajax call to query this item has been already bidded
						// by this person
						var url_bid = 'http://localhost:8080/bid-item/status/'
								+ itemId;
						$.ajax({
							type : "GET",
							dataType : 'json',
							url : url_bid,
							async : false,
							contentType : "application/json; charset=utf-8",
							success : function(data, status, xhr) {
								console.log(data);
								$("#bid-item-info-body").html(
										'You have previously bid on this item for <strong>$ '
												+ data.bidAmount
												+ '</strong> in '
												+ data.createdDate);
								$('#bidItemInfoModal').modal('show');
							},
							error : function(xhr, status, error) {
								$('#bidItemModal').modal('show');
								console.log(status);
							}
						});
					});

			$('img[class^="card-img-top"]').on(
					'click',
					function(event) {
						itemId = $(this).data('id');
						$("#itemId").val(itemId);
						var url_item_info = 'http://localhost:8080/item/'
							+ itemId;
						$('#item-info-1').empty();
						$('#item-info-2').empty();
						$('#item-info-3').empty();
						$.ajax({
							type : "GET",
							dataType : 'json',
							url : url_item_info,
							async : false,
							contentType : "application/json; charset=utf-8",
							success : function(data, status, xhr) {
								console.log(data);
								$("#item-info-1").append( "<div class=\"col-md-6\"><b>Item Name</b>: "+data.title+"</div>");
								$("#item-info-1").append( "<div class=\"col-md-6\"><b>Artist Name</b>: "+data.artistName+"</div>");
								$("#item-info-1").append( "<div class=\"col-md-6\"><b>Date of Creation</b>: "+data.dateOfCreation+"</div>");
								
								$("#item-info-2").append( "<div class=\"col-md-6\"><b>Classification</b>: "+data.classification+"</div>");
								$("#item-info-2").append( "<div class=\"col-md-6\"><b>Estimated Price</b>: $"+data.estimatePrice+"</div>");
								
								$("#item-info-3").append( "<div class=\"col-md-2\"><b>Description</b>:</div>");
								$("#item-info-3").append( "<div class=\"col-md-10\">"+data.description+"</div>");
								
								$('#itemInfoModal').modal('show');
							},
							error : function(xhr, status, error) {
								console.log(status);
							}
						});
					});
			
			$("#btn-bid-save").on('click', function(event) {
				event.preventDefault();
				error_bid_amount = checkBidAmount();
				error_message = checkMessage();
				
				if (error_bid_amount == false && error_message == false) {
					$("#item-bid-form").submit();
					return true;
				} else {
					console.log("INSIDE ELSE");
					event.preventDefault();
					event.stopPropagation()
					return false;
				}

			});

			$("#bid-amt").focusout(function() {
				console.log('inside bid-amt focus out')
				checkBidAmount();
			});

			$("#message-text").focusout(function() {
				console.log('inside bid-amt focus out')
				checkMessage();
			});

			function checkBidAmount() {
				var bid_amount = $("#bid-amt").val();
				var numberRegex = /^[+-]?\d+(\.\d+)?([eE][+-]?\d+)?$/;
				if (bid_amount == "") {
					$('#bid-amt-group').addClass('has-error');
					$('#bid-amt-group').append(
							'<div class="help-block">' + "Required Field"
									+ '</div>');
					return true;
				} else if (!numberRegex.test(bid_amount)) {
					$('#bid-amt-group').addClass('has-error');
					$('#bid-amt-group').append(
							'<span class="help-block">'
									+ "Should be valid number" + '</span>');
					return true;
				} else {
					$('#bid-amt-group').removeClass('.has-error');
					$('.help-block').remove();
					return false;
				}
			}

			function checkMessage() {
				var message = $("#message-text").val();
				if (message == "") {
					$('#message-group').addClass('has-error');
					$('#message-group').append(
							'<div class="help-block">' + "Required Field"
									+ '</div>');
					return true;
				} else {
					$('#message-group').removeClass('.has-error');
					$('.help-block').remove();
					return false;
				}
			}
		});