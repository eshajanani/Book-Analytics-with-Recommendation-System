package functionality;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public class ItemRecommend {

	public void recommendItems(long uID) {
		try {
			DataModel dm = new FileDataModel(new File("data/rate.csv"));

			// ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
			TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(dm);

			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);

			int x = 1;
//			for (LongPrimitiveIterator users = dm.getUserIDs(); users.hasNext();) {
//				// long itemId = items.nextLong();
//				long userId = users.nextLong();
			long userId =uID;
				List<RecommendedItem> recommendations = recommender.recommend(userId, 5);

				for (RecommendedItem recommendation : recommendations) {
					System.out.println(userId + "," + recommendation.getItemID() + "," + recommendation.getValue());
				
				}
//				x++;
//				if (x > 10)
//					break;
//
//			}

		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("There was a Taste Exception");
			e.printStackTrace();
		}

	}

}