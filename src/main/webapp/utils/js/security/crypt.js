(function($w) {
	if(typeof $w.CryptUtils === 'undefined')
		var CryptUtils = $w.CryptUtils = {};
	CryptUtils.encrypt = function(message, key){
		if(!message){
			return '';
		}
		//alert(CryptUtils.encrypt.caller);
		return DESUtils.encryptDES(message, key);
	};
	CryptUtils.decrypt = function(message, key){
		if(!message){
			return '';
		}
		//alert(CryptUtils.decrypt.caller);
		return DESUtils.decryptDES(message, key);
	};
})(window);