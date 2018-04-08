function range(start, end) {
    var foo = [];
    for (var i = start; i <= end; i++) {
        foo.push(i);
    }
    return foo;
};

$( document ).ready(function() {
  $.get( "/api/content/c1/stats", function( d ) {
    console.log(d);
    new Chart(document.getElementById("line-chart"), {
      type: 'line',
      data: {
        labels: JSON.parse(d).map( function(item) { return item["time"] }),
        datasets: [{
            data: JSON.parse(d).map( function(item) { return item["score"] }),
            label: "Где смешно",
            borderColor: "#3e95cd",
            fill: false
          }
        ]
      },
      options: {
        title: {
          display: true,
          text: 'Где смешно'
        }
      }
    });
  });
});
