package chapter10;
/**
 * 
 * 数据结构：链表
 * 
 * 由一系列的节点组成
 * 每个节点由三个部分组成:1.指向上一个节点地址的变量 2.当前节点的值 3.下一个节点的地址的变量
 * 由数据结构可以看到在中间进行插入和删除的时候效率会比较高一点
 * 
 * 由于java虽然没有指针，但是引用型的变量其实是就是一个地址，跟指针没有太大的区别，以下用面向对象的方式实现
 * 
 * @author 滑德友
 * @since 2018年5月2日15:57:44
 *
 */
public class LinkList2 {

	LinkList2Node frist;
	LinkList2Node last;
	int size;

	public LinkList2(){
		
		frist = null;
		last = null;
		size = 0;
	}
	
	public void addElement(Object element){
		
		LinkList2Node node = new LinkList2Node();
		node.value = element;
		
		if(size == 0){
			node.provious = null;
			node.next = null;
			frist = node;
			last = node;
		}else{
			last.next = node;
			node.provious = last;
			node.next = null;
			last = node;
		}
		
		size++;
		
	}
	
	public Object getElementByIndex(int index){
		
		if(index < 0 || index > size - 1){
			return null;
		}
		
		LinkList2Node node = frist;
		if( index == 0){
			return node.value;
		} else{
			for (int i = 1; i < size; i++) {
				node = node.next;
			}
			return node.value;
		}
		
	}
	
	public int getElementByValue(Object value){
		
		LinkList2Node node = frist;
		
		if( node.value.equals(value)){
			return 0;
		}else{
			for(int i = 1; i < size; i++){
				node = node.next;
				if(node.value.equals(value)){
					return i;
				}
			}
			return -1;
		}
		
	}
	
	public void deleteElementByIndex( int index){
		
		if(index < 0 || index > size - 1){
			return ;
		}
		
		LinkList2Node node = frist;
		if( index == 0){
			
			return ;
		} else{
			for (int i = 1; i < size; i++) {
				node = node.next;
			}
			return ;
		}
	}
	
}
