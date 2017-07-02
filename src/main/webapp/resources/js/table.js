(function getAllContents() {
  $.ajax({
    url: 'http://localhost:9001/vveb/api/v1/contents',
    type: 'GET',
    contentType: 'application/json',
    success: function(contents) {
      renderTableFiles(contents.content);
    }
  });
})();

function getFolderContent(folder) {
  $.ajax({
    url: 'http://localhost:9001/vveb/api/v1/contents/' + folder,
    type: 'GET',
    contentType: 'application/json',
    success: function(contents) {
      renderTableFiles(contents);
    }
  });
}

function renderTableFiles(data) {
  $('.contents-table').append('<thead><tr><th>Type</th><th>Name</th></tr></thead>');
  for(var i in data) {
    console.log(data[i]);
    renderRowContents(data[i]);
  }
}

function renderRowContents(rowData) {
    var row = $('<tr />')
    $('.contents-table').append(row);

    if(rowData.directory){
      //TODO: add picture
      row.append($('<td>directory</td>'));
      row.append($('<td onclick="getFolderContent("' + rowData.encodedPath +')>' + rowData.name + '</a></td>'));
    } else {
      row.append($('<td>file</td>'));
      row.append($('<td><a href=api/v1/contents/' + rowData.encodedPath +'>'  + rowData.name + '</a></td>'));
    }
    
}
