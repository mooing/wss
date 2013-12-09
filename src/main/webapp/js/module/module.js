// module
function toTreeObj( arr ){
	var res = [];
	// parent
	$(arr).filter(function(){
		if(+this.pId === 0) return true;
	}).each(function(i,v){
		var obj = {};
		obj.id = v.id;
		obj.text = v.name;
		obj.state = "closed";
		obj.children = [];
		res.push( obj );
	});
	// children
	$(arr).filter(function(){
		if(+this.pId !== 0) return true;
	}).each(function(i,v){
		var obj = {},
			pid;
		obj.id = v.id;
		obj.text = v.name;
		obj.children = [];
		pid = +v.pId;
		$(res).each(function(){
			if( +this.id === pid){
				this.children.push( obj );
			}
		});
	});
	return res;
}

$.getJSON("/module/alltree?guid="+new Date().getTime(),function(data){
	data = toTreeObj( data );
	$('#module-tree').tree({
	    "data":data
	});
});


