var LRUCache = function(capacity) {
this.capacity=capacity;
this.data={};
this.queue=[];
};

LRUCache.prototype.get = function(key) {
if(this.data[key]){
let idx=this.queue.indexOf(key);
this.queue.splice(idx,1);
this.queue.unshift(key);
return this.data[key];
}
return -1;
};

LRUCache.prototype.put = function(key, value) {
    var that = this
if(this.data[key]){
delete this.data[key];
let idx=this.queue.indexOf(key);
this.queue.splice(idx,1);
}
if(this.queue.length >= this.capacity){
let toBeRemoved=this.queue.pop();
var keyforValue = Object.keys(that.data).find(key =>  
                    that.data[key] === toBeRemoved); 
    delete this.data[toBeRemoved]
}
this.data[key]=value;
this.queue.unshift(key);
};
