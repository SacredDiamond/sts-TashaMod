package CorruptedMod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnCardDrawPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CorruptedMod.CorruptedBase;

//Gain 1 dex for the turn for each card played.

public class RapidFirePower extends AbstractPower implements OnCardDrawPower  {
    public AbstractCreature source;

    public static final String POWER_ID = CorruptedMod.CorruptedBase.makeID("RapidFirePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RapidFirePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("focus");
        this.source = source;
        canGoNegative = false;

    }



    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        }
        
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
    	this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }



	@Override
	public void onCardDraw(AbstractCard arg0) {
		if (arg0.hasTag(CorruptedBase.Ammo)) {
			
	        AbstractDungeon.actionManager
            .addToBottom(new DamageRandomEnemyAction(new DamageInfo(
            		owner, this.amount, DamageType.THORNS), AbstractGameAction.AttackEffect.POISON));
	        
	        AbstractDungeon.actionManager
            .addToBottom(new DamageRandomEnemyAction(new DamageInfo(
            		owner, this.amount, DamageType.THORNS), AbstractGameAction.AttackEffect.POISON));
		}
		
	}

}