<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Fine Uploader - jQuery Wrapper Minimal Demo</title>
    <link href="/css/fineuploader-3.5.0.css" rel="stylesheet">
  </head>
  <body>
    <div id="jquery-wrapped-fine-uploader"></div>
 
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery.fineuploader-3.5.0.js"></script>
    <script>
    $(document).ready(function() {
        $('#jquery-wrapped-fine-uploader').fineUploader({
          request: {
            endpoint: '/action/imgupload'
          },
          multiple: false,
          validation: {
            allowedExtensions: ['jpeg', 'jpg', 'png'],
            sizeLimit: 512000 // 500 kB = 50 * 1024 bytes 
          },
          text: {
            uploadButton: 'Click or Drop'
          }
        }).on('complete', function(event, id, fileName, responseJSON) {
          if (responseJSON.success) {
            $(this).append('<img src="'+responseJSON.url+'" >');
          }
        });
      });
    </script>
  </body>
</html>