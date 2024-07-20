package com.chailotl.particular.sushi_bar.mixin.lavender;

import com.chailotl.particular.sushi_bar.lavender.SushiBook;
import com.chailotl.particular.sushi_bar.lavender.SushiFeatureProvider;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import io.wispforest.lavender.book.Book;
import io.wispforest.lavender.client.LavenderBookScreen;
import io.wispforest.lavender.md.compiler.BookCompiler;
import io.wispforest.lavendermd.MarkdownFeature;
import io.wispforest.lavendermd.MarkdownProcessor;
import io.wispforest.owo.ui.core.ParentComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(LavenderBookScreen.class)
public class InjectLavenderBookScreen
{
	@Final
	@Shadow
	private BookCompiler.ComponentSource bookComponentSource;

	@Inject(
		method = "<init>(Lio/wispforest/lavender/book/Book;Z)V",
		at = @At(
			value = "INVOKE",
			target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
		)
	)
	private void useSushiFeatures(Book book, boolean isOverlay, CallbackInfo ci, @Local LocalRef<MarkdownProcessor<ParentComponent>> processor)
	{
		if (((SushiBook)(Object) book).getSushiFeatures())
		{
			processor.set(processor.get().copyWith(new SushiFeatureProvider().createFeatures(bookComponentSource).toArray(MarkdownFeature[]::new)));
		}
	}
}