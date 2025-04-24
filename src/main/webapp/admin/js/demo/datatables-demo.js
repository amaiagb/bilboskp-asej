// Call the dataTables jQuery plugin
$(document).ready(function() {
console.log("¿Existe la tabla?", $('#dataTable').length);
  $('#dataTable').DataTable({
        "columnDefs": [
            {
                "targets": "_all",  // Deshabilitar orden en la primera columna (índice 0)
                "orderable": false,  // Deshabilitar el ordenamiento
            }
        ],
        "searching": false, 
        "order": []  // Deshabilitar el ordenamiento por defecto al cargar la tabla
    });
});
