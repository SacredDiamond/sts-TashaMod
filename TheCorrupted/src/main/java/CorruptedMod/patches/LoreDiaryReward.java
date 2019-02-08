package CorruptedMod.patches;

import basemod.abstracts.CustomReward;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import CorruptedMod.CorruptedBase;

public class LoreDiaryReward extends CustomReward {
    private static final Texture ICON = new Texture(Gdx.files.internal("CorruptedResources/images/relics/placeholder_relic.png"));

    public LoreDiaryReward() {
        super(ICON, "Lore Card Reward", LoreDiaryPatch.LoreDiaryReward);

    	ArrayList<AbstractCard> LoreList = new ArrayList<AbstractCard>();

    	
    	for (AbstractCard c : CardLibrary.getAllCards()) {
    	if(c.hasTag(CorruptedBase.Lore)) LoreList.add(c.makeStatEquivalentCopy());
    	}
    	
        AbstractDungeon.player.getCardPool(LoreList);
        
        this.cards = LoreList;
        /*                                     */


    }

    @Override
    public boolean claimReward() {


    		if(AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {

    			AbstractDungeon.cardRewardScreen.open(this.cards, this, "Pick A Lore Card.");

    			AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;

    		}

    		return false;
    }
}