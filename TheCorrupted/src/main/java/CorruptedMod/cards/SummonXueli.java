package CorruptedMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CorruptedMod.CorruptedBase;
import CorruptedMod.patches.AbstractCardEnum;
import CorruptedMod.powers.CommonPower;
import CorruptedMod.powers.Mana;
import basemod.abstracts.CustomCard;

public class SummonXueli extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Hold Place Gain 1(2) Keywords(s).
     */


    // TEXT DECLARATION 

    public static final String ID = CorruptedMod.CorruptedBase.makeID("SummonDia");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = CorruptedBase.makePath(CorruptedBase.DEFAULT_UNCOMMON_POWER);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.DEFAULT_GRAY;

    private static final int COST = 1;

    // /STAT DECLARATION/


    public SummonXueli() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        
    }


    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new SummonXueli();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.initializeDescription();
        }
    }
}