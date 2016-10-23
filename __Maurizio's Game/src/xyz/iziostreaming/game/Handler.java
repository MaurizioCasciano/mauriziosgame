package xyz.iziostreaming.game;

import java.awt.Graphics;
import java.util.ArrayList;

//GESTORE
public class Handler {
	/**
	 * Richiama il metodo astratto GameObject.tick() su tutti gli oggetti del gioco.
	 */
	public void tick(){
		for(int i = 0; i < this.object.size(); i++){
			GameObject tempGameObject = object.get(i);
			
			tempGameObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < this.object.size(); i++){
			GameObject tempGameObject = object.get(i);
			
			tempGameObject.render(g);
		}
	}
	
	public void addGameObject(GameObject gameObject){
		this.object.add(gameObject);
	}
	
	public void removeGameObgect(GameObject gameObject){
		this.object.remove(gameObject);
	}
	
	public void clearEnemys(){
		for(int i = 0; i < this.object.size(); i++){
			GameObject tempGameObject = object.get(i);
			if(tempGameObject.getId() != ID.Player){
				this.object.remove(i);
				i--;
			}
		}
	}
	
	
	
	
	ArrayList<GameObject> object = new ArrayList<>();
}