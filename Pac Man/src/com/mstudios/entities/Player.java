package com.mstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mstudios.main.Game;
import com.mstudios.world.Camera;
import com.mstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	public BufferedImage sprite_left;
	
	public int lastDir = 1;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(48,0,16,16);
	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir = 1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir = -1;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
		}
		verificarPegarFruta();
		
		if(Game.frutas_contagem == Game.frutas_atual){
			//System.out.println("Ganhamos o jogo!");
			World.restartGame();
		}
	}
	
	public void verificarPegarFruta(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity current = Game.entities.get(i);
			if(current instanceof Fruta){
				//Se for fruta eu posso fazer a verificaçã para ver se está colidindo.
				if(Entity.isColidding(this, current)){
					//aqui o this é o jogador.
					//Se estiver colidindo.
					Game.frutas_atual++;
					Game.entities.remove(i);
					return; //Porque eu não quero que fique verificando mais de uma vez.
				}
			}
		}
	}

	public void render(Graphics g){
		if(lastDir ==1){ //Quer dizer que ele está apontando para a direita.
			super.render(g); //Método pai.
		}else{ //agora vamos carregar o sprite para a esquerda.
			g.drawImage(sprite_left, this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
	}
	


}
