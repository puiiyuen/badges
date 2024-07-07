import { OpenAPIRoute } from 'chanfana'
import { z } from 'zod'

import { acceptedRatesBadge } from '../../services/accepted-rates'
import {
  difficultiesParamV1,
  shieldExtraConfigsParams,
  successfulResponse,
  usernameParam,
} from '../common'

export class AcceptedRate extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode v1'],
    request: {
      query: z
        .object({})
        .merge(difficultiesParamV1)
        .merge(shieldExtraConfigsParams),
      params: usernameParam,
    },
    responses: {
      200: successfulResponse,
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { difficulty, ...shieldExtraConfigs } = data.query
    const shield = await acceptedRatesBadge(
      username,
      difficulty[0].toUpperCase() + difficulty.slice(1),
      shieldExtraConfigs
    )
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
