package CorruptedMod.cards.Mana;

import CorruptedMod.cards.AbstractCorrCard;
import CorruptedMod.powers.Mana;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import CorruptedMod.CorruptedBase;
import CorruptedMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class aWeakDiamond extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     * 
     * Hold Place Gain 1(2) Keywords(s).
     */


    // TEXT DECLARATION 

    public static final String ID = CorruptedMod.CorruptedBase.makeID("aWeakDiamond");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = CorruptedBase.makePath(CorruptedBase.DEFAULT_COMMON_POWER);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = AbstractCardEnum.DEFAULT_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 2;

    // /STAT DECLARATION/


    public aWeakDiamond() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (magic(2)) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, Mana.POWER_ID, 2));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new MetallicizePower(p, this.magicNumber), this.magicNumber));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new PlatedArmorPower(p, this.magicNumber * 2), this.magicNumber * 2));

        }
    }

    boolean magic (int min) {
        if (AbstractDungeon.player.hasPower(Mana.POWER_ID)) {

            return AbstractDungeon.player.getPower(Mana.POWER_ID).amount >= min;

        }
        return false;
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new aWeakDiamond();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.name = "is no Diamond at all";
            this.initializeTitle();
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }
}