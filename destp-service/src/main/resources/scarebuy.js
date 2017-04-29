
function createHtml5Str(){
    var p = 1.1

}

function ShieldEncoder(k,j){
    var h = k;
    var g = handle(j);

    h = first(h);
    h = this.encoder(h, g);
    h = last(h);
    h = this.encoder(h, g);
    h = first(h);
    h = this.encoder(h, g);
    h = first(h);
    h = last(h);
    return encodeURIComponent(h)
}

function encoder(k,j){
    var g = '';
    for (var h = 0; h < k.length; h++) {
        g += String.fromCharCode(k.charCodeAt(h) ^ j)
    }
    return g
}

function handle(g){
    return this.hash(g) & (g.length - 1)
}

function hash(l){
    l = l + '';
    var k = 0,
        m = 0;
    var g = l.length;
    for (var j = 0; j < g; j++) {
        k = 31 * k + l.charCodeAt(m++);
        if (k > 2147483647 || k < 2147483648) {
            k = k & 4294967295
        }
    }
    return k
}

function first(h){
    var k = [];
    var l = '';
    for (var g = 0; g < h.length; g++) {
        l += h.charAt(g);
        if (l.length == 5) {
            k.push(reverse(l));
            l = ''
        }
    }
    if (l.length < 5) {
        k.push(this.reverse(l));
        l = ''
    }
    for (var j in k) {
        l += k[j]
    }
    return l
}

function last(h){
    var k = [
    ];
    var l = '';
    for (var g = h.length - 1; g >= 0; g--) {
        l += h.charAt(g);
        if (l.length == 5) {
            k.push(this.reverse(l));
            l = ''
        }
    }
    if (l.length < 5) {
        k.push(this.reverse(l));
        l = ''
    }
    for (var j in k) {
        l += k[j]
    }
    return l
}

function reverse(h){
    var g = h.split('');
    g.reverse();
    return g.join('')
}


