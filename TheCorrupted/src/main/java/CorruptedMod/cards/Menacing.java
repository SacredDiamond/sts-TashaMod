package CorruptedMod.cards;

import CorruptedMod.CorruptedBase;
import CorruptedMod.patches.AbstractCardEnum;
import CorruptedMod.powers.MenacingPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Menacing extends AbstractCorrCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = CorruptedBase.makeID("Menacing");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = CorruptedBase.makePath(CorruptedBase.DEFAULT_COMMON_SKILL);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCardEnum.DEFAULT_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 5;

    private static final int turns = 2;
    private static final int amount = 1;

    // /STAT DECLARATION/


    public Menacing() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
       this.block = this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = turns;
        this.SecondMagicNumber = this.BaseSecondMagicNumber = amount;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i<this.timesUpgraded+1; i++ ) {
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(p, p, this.block));
        }

AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MenacingPower(p, p, magicNumber, SecondMagicNumber)));
    }

    // Which card to return when making a copy of this card.
    @Override
    public AbstractCard makeCopy() {
        return new Menacing();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.UpgradeSecondMagicNumber(1);
            this.initializeDescription();
        }
    }
}