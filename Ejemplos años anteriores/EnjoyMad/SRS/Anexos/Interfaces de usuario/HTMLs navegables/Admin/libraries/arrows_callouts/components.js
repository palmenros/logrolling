/********************************************************************************************/
/********************************************************************************************/
/**************************            ARROWS CALLOUTS             **************************/
/********************************************************************************************/
/********************************************************************************************/

/************************************* COMMON COMPONENT VARIABLES AND PROPERTIES *************************************/
var _library = 'arrows_callouts';
var _path = '/arrows_callouts/';

/************************************* COMPONENT TYPES *************************************/



//TYPE: ARROWS
prx.types.arrow = {
	name: "arrow"
	,onDisplay: function(item,containerid,symbol) {
		
		var _id = (!containerid) ? item.id : containerid+'-'+item.id;
		var dims = prx.componentsHelper.getRealDims(item, symbol);

		if(prx.componentsHelper.getProp(item.filledArrow,'boolean') == "true") { item.filledArrow = true; }
		if(prx.componentsHelper.getProp(item.filledArrow,'boolean') == "false") { item.filledArrow = false; }
		if(prx.componentsHelper.getProp(item.isDashed,'boolean') == "true") { item.isDashed = true; }
		if(prx.componentsHelper.getProp(item.isDashed,'boolean') == "false") { item.isDashed = false; }

		var cR = '<div id="' + _id + '" ' + prx.items.getComponentBaseAttributes(item, containerid, symbol)  + ' class="' + prx.items.getComponentBaseClasses(item, containerid, symbol) + ' box pos type-arrow '+prx.componentsHelper.getProp(item.arrowType,'other')+'" ';
		cR += 'data-border-width="'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'" data-dashed="'+prx.componentsHelper.getProp(item.isDashed,'boolean')+'" data-filled="'+prx.componentsHelper.getProp(item.filledArrow,'boolean')+'" ';
		cR += 'data-direction-arrow="'+prx.componentsHelper.getProp(item.arrowDirection,'other')+'">';
		
		cR += '<style>';
		cR += prx.items.getComponentBaseStyle(item, containerid, symbol);
		cR += '.path-'+_id+' { stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+'; stroke-linecap:'+prx.componentsHelper.getProp(item.capType,'other')+'; width:100%; height:100%; }';
		if( prx.componentsHelper.getProp(item.filledArrow,'boolean') ) {
			cR += '#arrowPath-'+_id+' { fill: '+prx.componentsHelper.getProp(item.borderColor,'color-fill')+'; }';
		}
		else {
			cR += '#arrowPath-'+_id+' { fill: none; }';
		}
		if (prx.componentsHelper.getProp(item.arrowName,'other') == "arrow3") {
			cR += '#detailPath-'+_id+' { fill: '+prx.componentsHelper.getProp(item.borderColor,'color-fill')+'; }';
		}
		else {
			cR += '#detailPath-'+_id+' { fill: none; }';
		}
		cR += '</style>';
		cR += prx.items.getComponentPrependDivs(item, containerid, symbol);
						
		cR += '<div id="'+prx.componentsHelper.getProp(item.arrowType,'other')+'-' + _id + '" class="arrow-wrapper ">';
		
		cR += '<svg id="svg-'+ _id +'" viewBox="0 0 '+(dims.width)+' '+(dims.height)+'" width="100%" height="100%" preserveAspectRatio="none" xmlns="http://www.w3.org/2000/svg" version="1.1">';
		
		if( prx.componentsHelper.getProp(item.arrowType,'other') == 'curved' || 
			prx.componentsHelper.getProp(item.arrowType,'other') == 'arced' || 
			prx.componentsHelper.getProp(item.arrowType,'other') == 'right-angle') {
			var path = prx.componentsHelper.drawArrowCurved( item, prx.componentsHelper.getProp(item.arrowType,'other'), dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
		}
		else if(prx.componentsHelper.getProp(item.arrowType,'other') == 'custom') {
			var path = prx.componentsHelper.drawArrowCustom( item, dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
		}
		else {
			var path = prx.componentsHelper.drawArrow( item, dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
		}			
		
		if(prx.componentsHelper.getProp(item.arrowType,'other') == 'custom') {
			cR += '<path d="'+path+'" id="customPath-'+ _id +'" class="liveUpdate-borderColor-stroke liveUpdate-borderColor-fill changeProperty-borderColor-stroke changeProperty-borderColor-fill changeProperty-borderWidth"';
				cR += 'style="fill: '+prx.componentsHelper.getProp(item.borderColor,'color-fill')+';" />';
		}
		else {
			var linePath = path.split("; ")[0];
			var arrowPath = path.split("; ")[1];
			
			// arrow lines
			cR += '<path d="'+linePath+'" id="linePath-'+ _id +'" class="path-'+ _id +' liveUpdate-borderColor-stroke changeProperty-borderColor-stroke changeProperty-borderWidth"';
				cR += 'style="fill: none; '+((prx.componentsHelper.getProp(item.isDashed,'boolean')) ? " stroke-dasharray: "+(5*(prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/3))+","+(10*(prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/3))+";" : "")+'" />';
				
			// arrow details (if any)
			if( prx.componentsHelper.getProp(item.arrowName,'other') == 'arrow2' || 
				prx.componentsHelper.getProp(item.arrowName,'other') == 'arrow3' ) {
				var detailPath = path.split("; ")[2];
				cR += '<path d="'+detailPath+'" id="detailPath-'+ _id +'" class="path-'+ _id +' liveUpdate-borderColor-stroke changeProperty-borderColor-stroke '+((prx.componentsHelper.getProp(item.arrowName,'other') == 'arrow3') ? "liveUpdate-borderColor-fill changeProperty-borderColor-fill " : "")+'changeProperty-borderWidth" />';
			}
			
			// arrow heads
			cR += '<path d="'+arrowPath+'" id="arrowPath-'+ _id +'" class="path-'+ _id +' liveUpdate-borderColor-stroke changeProperty-borderColor-stroke changeProperty-borderWidth'+((prx.componentsHelper.getProp(item.filledArrow,'other')) ? " liveUpdate-borderColor-fill changeProperty-borderColor-fill" : "")+'" />';
		}
			
		cR += '</svg>';
		
		cR += '</div>';
		cR += prx.items.getComponentAppendDivs(item, containerid, symbol);
		cR += '</div>';
		return cR;
	}
	,onResize: function(item,containerid,symbol) {

		var _id = (!containerid) ? item.id : containerid+'-'+item.id;
		var dims = prx.componentsHelper.getRealDims(item, symbol);

		if(prx.componentsHelper.getProp(item.filledArrow,'boolean') == "true") { item.filledArrow = true; }
		if(prx.componentsHelper.getProp(item.filledArrow,'boolean') == "false") { item.filledArrow = false; }
		if(prx.componentsHelper.getProp(item.isDashed,'boolean') == "true") { item.isDashed = true; }
		if(prx.componentsHelper.getProp(item.isDashed,'boolean') == "false") { item.isDashed = false; }

		document.getElementById('svg-'+ _id).setAttribute('viewBox', '0 0 '+(dims.width)+' '+(dims.height));
		
		if( prx.componentsHelper.getProp(item.arrowType,'other') == 'curved' || 
			prx.componentsHelper.getProp(item.arrowType,'other') == 'arced' || 
			prx.componentsHelper.getProp(item.arrowType,'other') == 'right-angle') {
			var path = prx.componentsHelper.drawArrowCurved( item, prx.componentsHelper.getProp(item.arrowType,'other'), dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
		}
		else if(prx.componentsHelper.getProp(item.arrowType,'other') == 'custom') {
			var path = prx.componentsHelper.drawArrowCustom( item, dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
		}
		else {
			var path = prx.componentsHelper.drawArrow( item, dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
		}

		if(prx.componentsHelper.getProp(item.arrowType,'other') == 'custom') {
			document.getElementById('customPath-'+ _id).setAttribute('d', path);
		}
		else {
			var linePath = path.split("; ")[0];
			var arrowPath = path.split("; ")[1];
			
			document.getElementById('linePath-'+ _id).setAttribute('d', linePath);
			if( prx.componentsHelper.getProp(item.arrowName,'other') == 'arrow2' || 
				prx.componentsHelper.getProp(item.arrowName,'other') == 'arrow3' ) {
				var detailPath = path.split("; ")[2];	
				document.getElementById('detailPath-'+ _id).setAttribute('d', detailPath);
			}
			document.getElementById('arrowPath-'+ _id).setAttribute('d', arrowPath);
		}
	}
	,interactions: [ prx.commonproperties.actions ]
	,propertyGroups: [		
        {
            caption: 'Style',
            properties: [
                 [
                     {
 	                    caption: 'Color',
 	                    name: 'borderColor',
 	                    proptype: 'border-color',
 	                    type: 'colorpicker',
 	                    value: function (item, name) {
 	                        return item.borderColor;
 	                    },
 	                    liveUpdate: 'stroke,fill',
 	                    changeProperty: {
 	                        caption: 'Arrow Color',
 	                        selector: '.changeProperty-borderColor',
 	                        property: 'stroke,fill',
 	                        transitionable: true
 	                    }
 	                }
                ],
                [
                 	{ 
                    	caption: 'Size', 
                    	name: 'borderWidth', 
                    	proptype: 'border-width', 
                    	type: 'combo-select', 
                    	value: function(item,name) { 
                    		return item.borderWidth; 
                    	}, 
                    	values: { min: 1, max: 15, step: 1 },
                    	changeProperty: { 
                    		caption: 'Size', 
                    		rerender: true
                    	} 
                    }
             	],
             	[
	                {
						caption: 'Dashed?'
						,name: 'isDashed'
						,type: 'onoff'
						,value: function(item,name) {
							if(typeof(item.isDashed)=="undefined") {
								return false;
							}
							return item.isDashed;
						}	
						,changeProperty: {
							caption: 'Dashed line toggle',
							rerender: true
						}
					}
                ]
            ]
        },
	  	{
  		   	caption: 'Arrowheads',
			properties: [
     			[
     			 	{
						caption: 'Filled?'
						,name: 'filledArrow'
						,type: 'onoff'
						,value: function(item,name) {
							if(typeof(item.filledArrow)=="undefined") {
								return false;
							}
							return item.filledArrow;
						}				
						,changeProperty: {
							caption: 'Filled arrowhead line toggle',
							rerender: true
						}
					}
     			]
     		]
  	    },
	  	{
  		   	caption: 'Arrow Direction',
			properties: [
     			[
     			 	{
						caption: false,
						proptype: 'arrow-direction',
						name: 'arrowDirection',
						type: 'radio',
						value: function(item, name) {
							return item.arrowDirection;
						},
						values: [
							{value: 'end',displayValue: '', icon: 'arrow-dir-end' },
							{value: 'both',displayValue: '', icon: 'arrow-dir-both' },
							{value: 'front',displayValue: '', icon: 'arrow-dir-start' }
						],
						changeProperty: {
							caption: 'Arrow direction',
							rerender: true
						}
					}
     			]
     		]
  	   }
	]
}

//TYPE = ARROWS
prx.types.arrow1 = prx.componentsHelper.cloneobject(prx.types.arrow);
prx.types.arrow1.name = 'arrow1';

//TYPE = ARROWS WITH ONLY ONE DIRECTION AT ANY TIME
prx.types.arrow2 = prx.componentsHelper.cloneobject(prx.types.arrow);
prx.types.arrow2.name = 'arrow2';
prx.types.arrow2.propertyGroups = prx.componentsHelper.editProperty(prx.types.arrow2.propertyGroups, 'arrowDirection', 'values', [{value: 'end',displayValue: '', icon: 'arrow-dir-end' },
	{value: 'front',displayValue: '', icon: 'arrow-dir-start' }]);

//TYPE = CUSTOM ARROWS
prx.types.arrow3 = prx.componentsHelper.cloneobject(prx.types.arrow);
prx.types.arrow3.name = 'arrow3';
prx.types.arrow3.propertyGroups = prx.componentsHelper.editProperty(prx.types.arrow3.propertyGroups, 'arrowDirection', 'values', [{value: 'end',displayValue: '', icon: 'arrow-dir-end' },
	{value: 'front',displayValue: '', icon: 'arrow-dir-start' }]);
prx.componentsHelper.removeProperties(prx.types.arrow3.propertyGroups, ['borderWidth','isDashed', 'filledArrow']);


//TYPE: BUBBLE
prx.types.bubble = {
	name: "bubble"
	,onDisplay: function(item,containerid,symbol) {
		
		var _id = (!containerid) ? item.id : containerid+'-'+item.id;
		var dims = prx.componentsHelper.getRealDims(item, symbol);
		
		var _props = prx.componentsHelper.getProp(item.textProperties,'props-text');
		
		var cwidth, cheight;
		
		if(	prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded' || 
			prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded-corners') {
			
			if(	prx.componentsHelper.getProp(item.tipDirection,'other') == 'left' || 
				prx.componentsHelper.getProp(item.tipDirection,'other') == 'right') {
				cwidth = dims.width - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
				cheight = dims.height - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
			}
			else if(prx.componentsHelper.getProp(item.tipDirection,'other') == 'top' || 
					prx.componentsHelper.getProp(item.tipDirection,'other') == 'bottom') {
				cwidth = dims.width - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
				cheight = dims.height - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
			}
		}
		else if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'think-cloud') {
			cwidth = dims.width - prx.componentsHelper.getProp(item.borderWidt,'num-border-width')*2;
			cheight = dims.height*0.6 - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;			
		}
		else {
			cwidth = dims.width - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
			cheight = dims.height - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;			
		}

		if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded-corners') {
			item.borderRadius = Math.min(dims.width+(Math.min(dims.width,dims.height)*0.30)-cwidth,dims.height+(Math.min(dims.width,dims.height)*0.30)-cheight)/2;
		}
		else if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded'){
			item.borderRadius = Math.min(dims.width+(Math.min(dims.width,dims.height)*0.30),dims.height+(Math.min(dims.width,dims.height)*0.30))/2;
		}

		var cR = '<div id="' + _id + '" ' + prx.items.getComponentBaseAttributes(item, containerid, symbol)  + ' class="' + prx.items.getComponentBaseClasses(item, containerid, symbol) + ' box pos type-bubble '+prx.componentsHelper.getProp(item.bubbleType,'other')+'" ';
		cR += 'data-border-width="'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'" data-direction-tip="'+prx.componentsHelper.getProp(item.tipDirection,'other')+'">';
		
		cR += '<style>';
		cR += prx.items.getComponentBaseStyle(item, containerid, symbol);
		cR += '#'+_id+' .bubble-text-container { width: '+cwidth+'px; height: '+cheight+'px; '+_props+' '+prx.componentsHelper.getProp(item.textFont,'font-family')+'; font-size: '+prx.componentsHelper.getProp(item.textSize,'num-text-size')+'px; text-align: '+prx.componentsHelper.getProp(item.textAlign,'align')+'; color: '+prx.componentsHelper.getProp(item.textColor,'color-text')+'; }';
		if( prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded' || 
			prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded-corners') {
			if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'bottom' || 
				prx.componentsHelper.getProp(item.tipDirection,'other') == 'right') {
				cR += '#'+_id+' .bubble-text-container { top: 0; left: 0; }';
			}
			if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'top' || 
				prx.componentsHelper.getProp(item.tipDirection,'other') == 'left') {
				cR += '#'+_id+' .bubble-text-container { bottom: 0; right: 0; }';
			}
		}
		else if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'think-cloud') {
			cR += '#'+_id+' .bubble-text-container { top: 0; left: 0; }';
		}
		else {
			cR += '#'+_id+' .bubble-text-container { top: '+((dims.height-cheight)/2)+'px; left: '+((dims.width-cwidth)/2)+'px; }';
		}
		cR += '</style>';
		cR += prx.items.getComponentPrependDivs(item, containerid, symbol);
						
		cR += '<div id="'+prx.componentsHelper.getProp(item.bubbleType,'other')+'-' + _id + '" class="bubble-wrapper">';
		
		cR += '<svg id="svg-'+ _id +'" viewBox="0 0 '+(dims.width)+' '+(dims.height)+'" preserveAspectRatio="none" xmlns="http://www.w3.org/2000/svg" version="1.1">';
						
		var path = prx.componentsHelper.drawBubble( item, prx.componentsHelper.getProp(item.bubbleType,'other'), dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width'));
				
		cR += '<path d="'+path+'" id="path-'+ _id +'" class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
			cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-fill')+'; ';
				cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+'; stroke-miterlimit:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+';" />';
		
		if( prx.componentsHelper.getProp(item.bubbleType,'other') == 'think-cloud' ) {
			
			if( prx.componentsHelper.getProp(item.borderWidth,'num-border-width') > (dims.width*0.03)) item.borderWidth = dims.width*0.03;
			
			if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'right' ) {
				cR += '<ellipse id="ellipse1-'+ _id +'" cx="'+(dims.width*0.73-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" cy="'+(dims.height*0.7-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" rx="'+(dims.width*0.1-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" ry="'+(dims.width*0.06-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'"';
					cR += 'class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
					cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-background')+'; ';
						cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+';" />';
				
				cR += '<ellipse id="ellipse2-'+ _id +'" cx="'+(dims.width*0.8-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" cy="'+(dims.height*0.85-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" rx="'+(dims.width*0.07-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" ry="'+(dims.width*0.04-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'"';
					cR += 'class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
					cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-background')+'; ';
						cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+';" />';
				
				cR += '<ellipse id="ellipse3-'+ _id +'" cx="'+(dims.width*0.85-prx.componentsHelper.getProp(item.borderWidth,'num-border-wdith')/2)+'" cy="'+(dims.height*0.95-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" rx="'+(dims.width*0.03-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" ry="'+(dims.width*0.03-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'"';
					cR += 'class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
					cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-background')+'; ';
						cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+';" />';
			}
			else if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'left' ) {
				cR += '<ellipse id="ellipse1-'+ _id +'" cx="'+(dims.width*0.27-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" cy="'+(dims.height*0.7-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" rx="'+(dims.width*0.1-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" ry="'+(dims.width*0.06-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'"';
					cR += 'class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
					cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-background')+'; ';
						cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+';" />';
				
				cR += '<ellipse id="ellipse2-'+ _id +'" cx="'+(dims.width*0.2-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" cy="'+(dims.height*0.85-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" rx="'+(dims.width*0.07-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" ry="'+(dims.width*0.04-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'"';
					cR += 'class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
					cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-background')+'; ';
						cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+';" />';
				
				cR += '<ellipse id="ellipse3-'+ _id +'" cx="'+(dims.width*0.15-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" cy="'+(dims.height*0.95-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" rx="'+(dims.width*0.03-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'" ry="'+(dims.width*0.03-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2)+'"';
					cR += 'class="liveUpdate-backgroundColor liveUpdate-borderColor changeProperty-backgroundColor changeProperty-borderColor changeProperty-borderWidth"';
					cR += 'style="fill:'+prx.componentsHelper.getProp(item.backgroundColor,'color-background')+'; ';
						cR += 'stroke:'+prx.componentsHelper.getProp(item.borderColor,'color-border')+'; stroke-width:'+prx.componentsHelper.getProp(item.borderWidth,'num-border-width')+'px; stroke-linejoin:'+prx.componentsHelper.getProp(item.joinType,'other')+';" />';
			}
		}
		cR += '</svg>';
		
		cR += '<div class="bubble-text-container liveUpdate-textColor">';
		cR += '<span data-editableproperty="text">' + prx.componentsHelper.getProp(item.text,'text-textarea') + '</span>';
		cR += '</div>';
		cR += '</div>';
		cR += prx.items.getComponentAppendDivs(item, containerid, symbol);
		cR += '</div>';

		delete item.borderRadius;

		return cR;
	}
	,onResize: function(item,containerid,symbol) {

		var _id = (!containerid) ? item.id : containerid+'-'+item.id;
		var dims = prx.componentsHelper.getRealDims(item, symbol);
						
		var cwidth, cheight;
		
		if( prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded' || 
			prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded-corners') {
			
			if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'left' || 
				prx.componentsHelper.getProp(item.tipDirection,'other') == 'right') {
				cwidth = dims.width - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
				cheight = dims.height - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
			}
			else if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'top' || 
				 	prx.componentsHelper.getProp(item.tipDirection,'other') == 'bottom') {
				cwidth = dims.width - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
				cheight = dims.height - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
			}
					
			$('#'+_id).find('.bubble-text-container').css({
				'width': cwidth + 'px',
				'height': cheight + 'px'
			});
		}

		if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded-corners') {
			item.borderRadius = Math.min(dims.width+(Math.min(dims.width,dims.height)*0.30)-cwidth,dims.height+(Math.min(dims.width,dims.height)*0.30)-cheight)/2;
		}
		else if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'rounded'){
			item.borderRadius = Math.min(dims.width+(Math.min(dims.width,dims.height)*0.30),dims.height+(Math.min(dims.width,dims.height)*0.30))/2;
		}
		
		if(prx.componentsHelper.getProp(item.bubbleType,'other') == 'cloud') {
			cwidth = dims.width - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;
			cheight = dims.height - (Math.min(dims.width,dims.height)*0.15) - prx.componentsHelper.getProp(item.borderWidth,'num-border-width')*2;			
			
			$('#'+_id).find('.bubble-text-container').css({
				'top': (dims.height-cheight)/2+'px',
				'left': (dims.width-cwidth)/2+'px'
			});
		}	

		document.getElementById('svg-'+ _id).setAttribute('viewBox', '0 0 '+(dims.width)+' '+(dims.height));
		document.getElementById('path-'+ _id).setAttribute('d', prx.componentsHelper.drawBubble( item, item.bubbleType, dims.width, dims.height, prx.componentsHelper.getProp(item.borderWidth,'num-border-width')));

		if( prx.componentsHelper.getProp(item.bubbleType,'other') == 'think-cloud' ) {
			
			if( prx.componentsHelper.getProp(item.borderWidth,'num-border-width') > (dims.width*0.03)) item.borderWidth = dims.width*0.03;
			
			if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'right' ) {
				document.getElementById('ellipse1-'+ _id).setAttribute('cx', dims.width*0.73-prx.componentsHelper.getProp(item.borderWidt,'num-border-width')/2);	
			
				document.getElementById('ellipse2-'+ _id).setAttribute('cx', dims.width*0.8-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);	
			
				document.getElementById('ellipse3-'+ _id).setAttribute('cx', dims.width*0.85-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			}
			else if( prx.componentsHelper.getProp(item.tipDirection,'other') == 'left' ) {
				document.getElementById('ellipse1-'+ _id).setAttribute('cx', dims.width*0.27-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
				
				document.getElementById('ellipse2-'+ _id).setAttribute('cx', dims.width*0.23-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);	
				
				document.getElementById('ellipse3-'+ _id).setAttribute('cx', dims.width*0.15-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			}		
			
			document.getElementById('ellipse1-'+ _id).setAttribute('cy', dims.height*0.7-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			document.getElementById('ellipse1-'+ _id).setAttribute('rx', dims.width*0.1-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			document.getElementById('ellipse1-'+ _id).setAttribute('ry', dims.width*0.06-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			
			document.getElementById('ellipse2-'+ _id).setAttribute('cy', dims.height*0.85-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			document.getElementById('ellipse2-'+ _id).setAttribute('rx', dims.width*0.07-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			document.getElementById('ellipse2-'+ _id).setAttribute('ry', dims.width*0.04-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			
			document.getElementById('ellipse3-'+ _id).setAttribute('cy', dims.height*0.95-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			document.getElementById('ellipse3-'+ _id).setAttribute('rx', dims.width*0.03-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
			document.getElementById('ellipse3-'+ _id).setAttribute('ry', dims.width*0.03-prx.componentsHelper.getProp(item.borderWidth,'num-border-width')/2);
		}
		
		delete item.borderRadius;

	}
	,editableProperties: [
  		{
  	    	caption: 'Text'
  	    	,name: 'text'
  	    	,type: 'textarea'
  	    	,value: function(item,name) {
  	    		if(typeof(item.text) == "undefined") { item.text = '' }
  	    		return item.text;
  	    	},
    		changeProperty: {
				caption: 'Text',
				property: 'text',
				selector: '.rectangle-text-container span',
				transitionable: false
			}
  	    }
  	]
	,interactions: [ prx.commonproperties.actions ]
	,propertyGroups: [	
		{
			caption: 'Text',
			properties: 
			[
				[
					{
						caption: false,
						name: 'textFont',
						proptype: 'font-family',
						type: 'select',
						value: function(item,name) {
							if(typeof(item.textFont) == "undefined") { item.textFont = 'sans-serif,Helvetica Neue,Arial' }
							return item.textFont;
						},
						values: function(){ return prx.comps.fonts }
			      		,changeProperty: {
							caption: ' Text font',
							selector: '.liveUpdate-textColor',
							property: 'font-family',
							transitionable: false
						 }
			
					},
					{
						caption: false,
						name: 'textSize',
						proptype: 'font-size',
						type: 'combo-select',
						value: function(item,name) {
							if(typeof(item.textSize) == "undefined") { item.textSize = 16 }
							return item.textSize;
						},
						values: prx.comps.textsize
			      		,changeProperty: {
							caption: ' Text size',
							selector: '.liveUpdate-textColor',
							property: 'font-size',
							transitionable: false
						 }
					},
			      	{
			      		caption: false,
			      		name: 'textColor',
			      		proptype: 'font-color',
			      		type: 'colorpicker',
			      		value: function(item,name) {
			      			if(typeof(item.textColor) == "undefined") { item.textColor = '#2E2E2E' }
			      			return item.textColor;
			      		},
			      		liveUpdate: 'color'
			      		,changeProperty: {
							caption: ' Text color',
							selector: '.liveUpdate-textColor',
							property: 'color',
							transitionable: true
						 }
			      	}
		      	],
				[
					{
						caption: false,
						name: 'textProperties',
						proptype: 'text-properties',
						type: 'checkbox',
						value: function(item,name) { if(typeof(item.textProperties) == "undefined") {item.textProperties = [];} return item.textProperties; },
						values: [
							{ value: 'bold', displayValue: '', icon: 'text-bold'},
							{ value: 'italic', displayValue: '', icon: 'text-italic'},
							{ value: 'underline', displayValue: '', icon: 'text-underline'}
						],
			      		changeProperty: {
							caption: 'Text Properties',
							rerender: true
			  			}
					},
			  		{
			  			caption: false,
			  			name: 'textAlign',
			  			proptype: 'text-align',
			  			type: 'radio',
			  			value: function(item,name) {
			  				if(typeof(item.textAlign) == "undefined") { item.textAlign = 'center' }
			  				return item.textAlign;
			  			},
			  			values: [
						    { value: 'left', displayValue: '', icon: 'align-left'}, 
						    { value: 'center', displayValue: '', icon: 'align-center'}, 
						    { value: 'right', displayValue: '', icon: 'align-right'}
					    ],
			  			changeProperty: {
							caption: 'Text Align',
							selector: '.liveUpdate-textColor',
							property: 'text-align',
							transitionable: false
			  			}
			  		}
		  		]
			]
		},
        {
            caption: 'Style',
            properties: [
                 [
                    {
                        caption: 'Background',
                        name: 'backgroundColor',
                        proptype: 'background-color',
                        type: 'colorpicker',
                        value: function (item, name) {
                            return item.backgroundColor;
                        },
                        liveUpdate: 'fill',
                        changeProperty: {
                            caption: 'Background color',
                            selector: '.changeProperty-backgroundColor',
                            property: 'fill',
                            transitionable: true
                        }
                    }
                ],
                [
	                {
		                caption: 'Border',
		                name: 'borderWidth',
		                proptype: 'border-width',
		                type: 'combo-select',
		                value: function(item,name) {
			                return item.borderWidth;
		                },
		                values: { min: 0, max: 20, step: 1 },
		                onChange: function (item, name) {
							if(item.borderWidth <= 0) { $('#property-roundJoin').hide();}
							else { $('#property-roundJoin').show();}
						},
		                changeProperty: {
			                caption: 'Border Width',
			                rerender: true
		                }
	                },
                    {
	                    caption: false,
	                    name: 'borderColor',
	                    proptype: 'border-color',
	                    type: 'colorpicker',
	                    value: function (item, name) {
	                        return item.borderColor;
	                    },
	                    liveUpdate: 'stroke',
	                    changeProperty: {
	                        caption: 'Border color',
	                        selector: '.changeProperty-borderColor',
	                        property: 'stroke',
	                        transitionable: true
	                    }
	                }
                ],
             	[
	                {
						caption: 'Rounded Border'
						,name: 'roundJoin'
						,type: 'onoff'
						,value: function(item,name) {
							if(typeof(item.roundJoin)=="undefined") {
								return false;
							}
							return item.roundJoin;
						}	
		                ,onChange: function (item, name) {
							if(item.roundJoin) { item.joinType = 'round';}
							else { item.joinType = 'miter';}
						}
		                ,hiddenByDefault: function(item) {
				            return (item.borderWidth <= 0);
			            }
						,changeProperty: {
							caption: 'Rounded border',
							rerender: true
						}
					}
                ]
            ]
        },
	  	{
  		   	caption: 'Tip Direction',
			properties: [
     			[
     			 	{
						caption: false,
						proptype: 'arrow-direction',
						name: 'tipDirection',
						type: 'radio',
						value: function(item, name) {
							return item.tipDirection;
						},
						values: [
							{value: 'bottom',displayValue: '', icon: 'bubble-tip-bottom'},
							{value: 'left',displayValue: '', icon: 'bubble-tip-left'},
							{value: 'top',displayValue: '', icon: 'bubble-tip-top'},
							{value: 'right',displayValue: '', icon: 'bubble-tip-right'}
						],
						changeProperty: {
							caption: 'Bubble Tip direction',
							rerender: true
						}
					}
     			]
     		]
  	   }
	]
}

//TYPE = ARROWS WITH ONLY ONE DIRECTION AT ANY TIME
prx.types.bubble2 = prx.componentsHelper.cloneobject(prx.types.bubble);
prx.types.bubble2.name = 'bubble2';
prx.types.bubble2.propertyGroups = prx.componentsHelper.editProperty(prx.types.bubble2.propertyGroups, 'tipDirection', 'values', [
	{value: 'left',displayValue: '', icon: 'bubble-tip-left'},
	{value: 'right',displayValue: '', icon: 'bubble-tip-right'}]);





/************************************************************************************************/
/************************************* COMPONENTS (OBJECTS) *************************************/
/************************************************************************************************/



/****** ARROWS ******/

prx.components.arrow1_arrow = {
	name: 'arrow1_arrow'
	,type: 'arrow1'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-160px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow1/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow1'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow1_arrow_curved = {
	name: 'arrow1_arrow_curved'
	,type: 'arrow1'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-210px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 25*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow1curved/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'curved'
	,arrowName: 'arrow1'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow1_arrow_arced = {
	name: 'arrow1_arrow_arced'
	,type: 'arrow1'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-260px -8px'
	,width: 50*prx.componentsHelper.getScale(_library)
	,height: 150*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow1arced/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'arced'
	,arrowName: 'arrow1'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow1_arrow_rightangled = {
	name: 'arrow1_arrow_rightangled'
	,type: 'arrow1'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-310px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 50*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow1angled/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'right-angle'
	,arrowName: 'arrow1'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow2_arrow = {
	name: 'arrow2_arrow'
	,type: 'arrow2'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-360px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow2/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow2'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow2_arrow_rightangled = {
	name: 'arrow2_arrow_rightangled'
	,type: 'arrow2'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-410px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 50*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow2angled/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'right-angle'
	,arrowName: 'arrow2'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow3_arrow = {
	name: 'arrow3_arrow'
	,type: 'arrow2'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-810px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow3/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow3'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow3_arrow_rightangled = {
	name: 'arrow3_arrow_rightangled'
	,type: 'arrow2'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-760px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 50*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow3angled/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'right-angle'
	,arrowName: 'arrow3'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow4_arrow = {
	name: 'arrow4_arrow'
	,type: 'arrow1'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-710px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow4/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow4'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow5_arrow = {
	name: 'arrow5_arrow'
	,type: 'arrow3'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-660px -8px'
	,width: 75*prx.componentsHelper.getScale(_library)
	,height: 50*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow5/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'custom'
	,arrowName: 'arrow5'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow6_arrow = {
	name: 'arrow6_arrow'
	,type: 'arrow3'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-610px -8px'
	,width: 50*prx.componentsHelper.getScale(_library)
	,height: 50*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow6/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'custom'
	,arrowName: 'arrow6'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow7_arrow = {
	name: 'arrow7_arrow'
	,type: 'arrow3'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-560px -8px'
	,width: 50*prx.componentsHelper.getScale(_library)
	,height: 50*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow7/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'custom'
	,arrowName: 'arrow7'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow8_arrow = {
	name: 'arrow8_arrow'
	,type: 'arrow3'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-460px -8px'
	,width: 90*prx.componentsHelper.getScale(_library)
	,height: 100*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/arrow8/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'custom'
	,arrowName: 'arrow8'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}


/****** ARROWS ******/

prx.components.bubble = {
	name: 'bubble'
	,type: 'bubble'
	,lib: _library
	,caption: 'Speech Bubble'
	,icon: '-60px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 100*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/bubble1/helper.png'
	,backgroundColor: 'C6C6C6'
	,borderColor: '555555'
	,borderWidth: 0*prx.componentsHelper.getScale(_library)
	,borderRadius: 0*prx.componentsHelper.getScale(_library)
	,textFont: 'sans-serif,Helvetica Neue,Arial'
	,textSize: 16*prx.componentsHelper.getScale(_library)
	,textColor:  '#383838'
	,textProperties: []
	,textAlign: 'center'
	,text: ''
	,bubbleType: 'rounded-corners'
	,tipDirection: 'bottom'
	,joinType: 'round'
	,roundJoin: true
	,actions:[]
}

prx.components.bubble_round = {
	name: 'bubble_round'
	,type: 'bubble'
	,lib: _library
	,caption: 'Speech Bubble'
	,icon: '-110px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 100*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/bubble2/helper.png'
	,backgroundColor: 'C6C6C6'
	,borderColor: '555555'
	,borderWidth: 0*prx.componentsHelper.getScale(_library)
	,borderRadius: 0*prx.componentsHelper.getScale(_library)
	,textFont: 'sans-serif,Helvetica Neue,Arial'
	,textSize: 16*prx.componentsHelper.getScale(_library)
	,textColor:  '#383838'
	,textProperties: []
	,textAlign: 'center'
	,text: ''
	,bubbleType: 'rounded'
	,tipDirection: 'bottom'
	,joinType: 'round'
	,roundJoin: true
	,actions:[]
}

prx.components.bubble_cloud = {
	name: 'bubble_cloud'
	,type: 'bubble'
	,lib: _library
	,caption: 'Speech Bubble'
	,icon: '-10px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 100*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/bubble3/helper.png'
	,backgroundColor: 'C6C6C6'
	,borderColor: '555555'
	,borderWidth: 0*prx.componentsHelper.getScale(_library)
	,borderRadius: 0*prx.componentsHelper.getScale(_library)
	,textFont: 'sans-serif,Helvetica Neue,Arial'
	,textSize: 16*prx.componentsHelper.getScale(_library)
	,textColor:  '#383838'
	,textProperties: []
	,textAlign: 'center'
	,text: ''
	,bubbleType: 'cloud'
	,tipDirection: 'bottom'
	,joinType: 'round'
	,roundJoin: true
	,actions:[]
}

prx.components.bubble_think_cloud = {
	name: 'bubble_think_cloud'
	,type: 'bubble2'
	,lib: _library
	,caption: 'Speech Bubble'
	,icon: '-860px -8px'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 125*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/bubble4/helper.png'
	,backgroundColor: 'C6C6C6'
	,borderColor: '555555'
	,borderWidth: 0*prx.componentsHelper.getScale(_library)
	,borderRadius: 0*prx.componentsHelper.getScale(_library)
	,textFont: 'sans-serif,Helvetica Neue,Arial'
	,textSize: 16*prx.componentsHelper.getScale(_library)
	,textColor:  '#383838'
	,textProperties: []
	,textAlign: 'center'
	,text: ''
	,bubbleType: 'think-cloud'
	,tipDirection: 'left'
	,joinType: 'round'
	,roundJoin: true
	,actions:[]
}


/****** DUMMIES ******/

prx.components.arrow = {
	name: 'arrow'
	,type: 'arrow'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-160px 0'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/dummy/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow1'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow1 = {
	name: 'arrow1'
	,type: 'arrow1'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-160px 0'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/dummy/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow1'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow2 = {
	name: 'arrow2'
	,type: 'arrow2'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-160px 0'
	,width: 150*prx.componentsHelper.getScale(_library)
	,height: 18*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/dummy/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'straight'
	,arrowName: 'arrow2'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.arrow3 = {
	name: 'arrow3'
	,type: 'arrow3'
	,lib: _library
	,caption: 'Arrow'
	,icon: '-160px 0'
	,width: 50*prx.componentsHelper.getScale(_library)
	,height: 25*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/dummy/helper.png'
	,backgroundColor: '555555'
	,borderColor: '555555'
	,borderWidth: 2*prx.componentsHelper.getScale(_library)
	,arrowType: 'custom'
	,arrowName: 'arrow5'
	,arrowDirection: 'front'
	,joinType: 'round'
	,capType: 'round'
	,isDashed: false
	,filledArrow: false
	,actions:[]
}

prx.components.bubble2 = {
	name: 'bubble2'
	,type: 'bubble2'
	,lib: _library
	,caption: 'Speech Bubble'
	,icon: '-160px 0'
	,width: 90*prx.componentsHelper.getScale(_library)
	,height: 125*prx.componentsHelper.getScale(_library)
	,helper: prx.url.devices+ _path + '/dummy/helper.png'
	,backgroundColor: 'C6C6C6'
	,borderColor: '555555'
	,borderWidth: 0*prx.componentsHelper.getScale(_library)
	,borderRadius: 0*prx.componentsHelper.getScale(_library)
	,textFont: 'sans-serif,Helvetica Neue,Arial'
	,textSize: 16*prx.componentsHelper.getScale(_library)
	,textColor:  '#383838'
	,textProperties: []
	,textAlign: 'center'
	,text: ''
	,bubbleType: 'think-cloud'
	,tipDirection: 'right'
	,joinType: 'round'
	,roundJoin: true
	,actions:[]
}