/*    */ package defaultmod.actions;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.curses.Decay;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;

import defaultmod.powers.DecayPower;
/*    */ 
/*    */ public class Decay2PoisonAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
private int multiplier;
/*    */   
/*    */   public Decay2PoisonAction(AbstractCreature target,AbstractCreature source , int m)
/*    */   {
/* 26 */     this.actionType = ActionType.DAMAGE;
/* 27 */     this.duration = 0.1F;
			this.multiplier = m;
			this.target = target;
			this.source = source;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {

	System.out.println("CHecking if " + this.target + "has Decay.");
			if(this.target.hasPower(Decay.ID) && this.target.getPower(Decay.ID).amount >0) {
				
				System.out.println("Calculating how much Poison to convert to");
				int poi = this.target.getPower(DecayPower.POWER_ID).amount * this.multiplier;
				
				System.out.println("removing decay and applying poison");
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, DecayPower.POWER_ID));
			
			AbstractDungeon.actionManager
			.addToBottom(new ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source, poi), poi));
			
/*    */   }
/*    */ }
}