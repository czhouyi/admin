Ext.define('index.view.extKindEditor', {
	extend : 'Ext.form.field.TextArea',
	alias : 'widget.extKindEditor',
	initComponent : function() {

		this.on("boxready", function(t) {
			this.inputEL = Ext.get(this.getId() + "-input");
			this.editor = KindEditor.create('textarea[name="' + this.name + '"]', {
				width : t.getWidth() - 105,
				height : t.getHeight(),
				resizeType : 1,
				allowPreviewEmoticons : false,
				allowImageUpload : false,
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview',
						'print', 'template', 'code', 'cut', 'copy', 'paste',
						'plainpaste', 'wordpaste', '|', 'justifyleft',
						'justifycenter', 'justifyright', 'justifyfull',
						'insertorderedlist', 'insertunorderedlist', 'indent',
						'outdent', 'subscript', 'superscript', 'clearhtml',
						'quickformat', 'selectall', '|', 'fullscreen', '/',
						'formatblock', 'fontname', 'fontsize', '|',
						'forecolor', 'hilitecolor', 'bold', 'italic',
						'underline', 'strikethrough', 'lineheight',
						'removeformat', '|', 'image', 'flash', 'media',
						'table', 'hr', 'emoticons', 'pagebreak', 'anchor',
						'link', 'unlink' ]
			});
		});
		this.callParent(arguments);
	},
	renderer : function() {
		this.editor.show();
	},
	setValue : function(value) {
		if (this.editor) {
			this.editor.html(value);
		}
	},
	reset : function() {
		if (this.editor) {
			this.editor.html('');
		}
	},
	setRawValue : function(value) {
		if (this.editor) {
			this.editor.html(value);
		}
	},
	getValue : function() {
		if (this.editor) {
			return this.editor.html();
		} else {
			return '';
		}
	},
	getRawValue : function() {
		if (this.editor) {
			return this.editor.html();
		} else {
			return '';
		}
	}
});